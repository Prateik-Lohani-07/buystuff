package com.buystuff.buystuff_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.dto.cart.UpdateCartDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpdateCartItemDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.cart.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/me/cart")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ApiResponse<CartDto> viewCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: viewCart controller");
		
		CartDto cartDto = cartService.viewCart(userPrincipal.getId());
		
		log.info("END: viewCart controller");
		return ApiResponse.success(
			"Successfully fetched cart", 
			cartDto
		);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/items")
	public ApiResponse<CartDto> addAllToCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@Valid @RequestBody List<UpsertCartItemDto> items
	) {
		log.info("START: addAllToCart controller");
		
		CartDto cartDto = cartService.addAllToCart(userPrincipal.getId(), items);
		
		log.info("END: addAllToCart controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(), 
			"Successfully added item to cart", 
			cartDto
		);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/items/{item_id}")
	public ApiResponse<CartDto> changeCartItem(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable(name = "item_id") Long itemId,
		@Valid @RequestBody UpdateCartItemDto item
	) {
		log.info("START: changeCartItem controller");
		
		CartDto cartDto = cartService.changeCartItem(userPrincipal.getId(), itemId, item);
		
		log.info("END: changeCartItem controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(), 
			"Successfully updated cart item", 
			cartDto
		);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping
	public ApiResponse<CartDto> updateCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@Valid @RequestBody UpdateCartDto updateCartDto
	) {
		log.info("START: updateCart controller");
		
		CartDto cartDto = cartService.updateCart(userPrincipal.getId(), updateCartDto);
		
		log.info("END: updateCart controller");
		return ApiResponse.success(
			"Successfully updated cart", 
			cartDto
		);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	public ApiResponse<Void> clearCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: clearCart controller");

		cartService.clearCart(userPrincipal.getId());

		log.info("END: clearCart controller");
		return ApiResponse.success(
			HttpStatus.NO_CONTENT.value(),
			"Successfully cleared cart", 
			null
		);
	}
}
