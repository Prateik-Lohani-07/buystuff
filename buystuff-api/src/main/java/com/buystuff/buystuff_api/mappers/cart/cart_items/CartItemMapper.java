package com.buystuff.buystuff_api.mappers.cart.cart_items;

import com.buystuff.buystuff_api.dto.cart.cart_item.CartItemDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.CartItem;
import com.buystuff.buystuff_api.entities.Product;

public abstract class CartItemMapper {
	public static CartItemDto toDto(CartItem entity) {
		CartItemDto dto = new CartItemDto();

		System.out.println(entity.getItemId());
		dto.setItemId(entity.getItemId());
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		dto.setProductCode(entity.getProduct().getProductCode());

		return dto;
	}

	public static CartItem toEntity(UpsertCartItemDto dto, Cart cart, Product product) {
		CartItem entity = new CartItem();

		entity.setPrice(product.getNetPrice());
		entity.setQuantity(dto.quantity());
		entity.setCart(cart);
		entity.setProduct(product);

		return entity;
	}
}
