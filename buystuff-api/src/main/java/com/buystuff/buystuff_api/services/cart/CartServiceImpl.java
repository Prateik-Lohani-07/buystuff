package com.buystuff.buystuff_api.services.cart;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.dto.cart.UpdateCartDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpdateCartItemDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.CartItem;
import com.buystuff.buystuff_api.entities.Coupon;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.exceptions.BadRequestException;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.exceptions.UnProcessableException;
import com.buystuff.buystuff_api.mappers.cart.CartMapper;
import com.buystuff.buystuff_api.mappers.cart.cart_items.CartItemMapper;
import com.buystuff.buystuff_api.repositories.CartItemRepository;
import com.buystuff.buystuff_api.repositories.ProductRepository;
import com.buystuff.buystuff_api.services.account.AccountService;
import com.buystuff.buystuff_api.services.coupon.CouponService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	
	private final AccountService accountService;
	private final CouponService couponService;

	private static enum QuantitySetMode {
		INCREMENT,
		SET
	}

	/**
	 * Updates a cart item quantity. Note that the item must exist in the first place. This API
	 * is idempotent.
	 */
	@Override
	@Transactional
	public CartDto changeCartItem(UUID accountId, UUID itemId, UpdateCartItemDto updateCartItemDto) {
		log.info("START: changeCartItem service");

		Account account = accountService.getAccount(accountId);
		Cart cart = account.getCart();
		
		CartItem item = cart.getItems()
			.stream()
			.filter(i -> i.getItemId().equals(itemId))
			.findFirst()
			.orElseThrow(() -> new NotFoundException(String.format("Item with id %s not found.", itemId.toString())));


		Product product = item.getProduct();
		if (!product.getIsActive()) {
			throw new BadRequestException("Product is no longer available");
		}
		if (updateCartItemDto.quantity() > product.getStock()) {
			throw new UnProcessableException("Requested quantity exceeds product stock");
		}

		item.setQuantity(updateCartItemDto.quantity());

		CartDto cartDto = CartMapper.toDto(cart, accountId);

		log.info("END: changeCartItem service");
		return cartDto;
	}


	/**
	 * Adds specified products to cart. In case the products already exist, it adds the specified quantity in the
	 * request to the existing quantity. Note that this API is not idempotent.
	 */
	@Override
	@Transactional
	public CartDto addAllToCart(UUID accountId, List<UpsertCartItemDto> upsertItems) {
		log.info("START: addAllToCart service");

		Account account = accountService.getAccount(accountId);
		Cart cart = account.getCart();

		bulkUpsertCart(cart, QuantitySetMode.INCREMENT, upsertItems);
		CartDto cartDto = CartMapper.toDto(cart, accountId);

		log.info("END: addAllToCart service");
		return cartDto;
	}


	/**
	 * Bulk updates all cart items: for each specified product in cart, sets the respective quantity. This API
	 * is idempotent.
	 */
	@Override
	public CartDto updateCart(UUID accountId, UpdateCartDto updateCartDto) {
		log.info("START: updateCart service");

		Account account = accountService.getAccount(accountId);
		Cart cart = account.getCart();

		bulkUpsertCart(cart, QuantitySetMode.SET, updateCartDto.items());
		String couponCode = Optional.ofNullable(updateCartDto.couponCode())
						.map(Function.identity())
						.orElse(null);
		
		if (couponCode != null) {
			Coupon coupon = couponService.getCouponByCouponCode(couponCode);
			cart.setCoupon(coupon);
		}

		CartDto cartDto = CartMapper.toDto(cart, accountId);

		log.info("END: updateCart service");
		return cartDto;
	}

	/**
	 * Deletes all items from the account's cart (hard delete). 
	 */
	@Override
	public void clearCart(UUID accountId) {
		log.info("START: clearCart service");

		Account account = accountService.getAccount(accountId);
		Cart cart = account.getCart();
		cartItemRepository.deleteByCart(cart);

		log.info("END: clearCart service");
	}

	private void bulkUpsertCart(
		Cart cart, 
		QuantitySetMode mode, 
		List<UpsertCartItemDto> upsertItems
	) {
		Set<UUID> productIds = 
			upsertItems.stream()
				.map(UpsertCartItemDto::productId)
				.collect(Collectors.toSet());

		if (upsertItems.size() != productIds.size()) {
			throw new BadRequestException("One or more duplicate product IDs specified");
		}

		List<Product> products = productRepository.findAllById(productIds);

		if (products.size() != productIds.size()) {
			throw new BadRequestException("One or more products specified do not exist");
		}

		List<CartItem> existingItems = cartItemRepository.findByCartAndProduct_IdIn(cart, productIds);

		Map<UUID, Product> productMap = 
			products.stream()
				.collect(Collectors.toMap(
					product -> product.getProductId(),
					Function.identity()
				));

		Map<UUID, CartItem> existingCartItemsMap = 
			existingItems.stream()
				.collect(Collectors.toMap(
					cartItem -> cartItem.getProduct().getProductId(), 
					Function.identity())
				);

		for (var dto: upsertItems) {
			Product product = productMap.get(dto.productId());
			CartItem existingItem = existingCartItemsMap.get(dto.productId());

			int baseQty = Optional.ofNullable(existingItem)
					.map(item -> item.getQuantity())
					.orElse(0);
			int newQty = (mode == QuantitySetMode.INCREMENT) ? baseQty + dto.quantity() : dto.quantity();
			if (newQty > product.getStock()) {
				throw new UnProcessableException("Requested quantity exceeds product stock");
			}

			if (existingItem != null) {
				int newQuantity = existingItem.getQuantity() + dto.quantity();
				existingItem.setQuantity(newQuantity);
			}
			else {
				CartItem newItem = CartItemMapper.toEntity(dto, cart, product);
				cart.getItems().add(newItem);
			}
		}
	}
	
}
