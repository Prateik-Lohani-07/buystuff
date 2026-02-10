package com.buystuff.buystuff_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.dto.cart.UpdateCartDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.cart.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/me/account/cart")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;

	@PostMapping("/items")
	public ApiResponse<CartDto> addAllToCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@RequestBody List<UpsertCartItemDto> items
	) {
		log.info("START: addAllToCart controllers");
		
		CartDto cartDto = cartService.addAllToCart(userPrincipal.getId(), items);
		
		log.info("END: addAllToCart controllers");
		return ApiResponse.success(
			HttpStatus.CREATED.value(), 
			"Successfully added item to cart", 
			cartDto
		);
	}

	@PatchMapping("/items/{item_id}")
	public ApiResponse<CartDto> changeCartItem(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable(name = "item_id") UUID itemId,
		@RequestBody UpsertCartItemDto item
	) {
		log.info("START: changeCartItem controllers");
		
		CartDto cartDto = cartService.changeCartItem(userPrincipal.getId(), itemId, item);
		
		log.info("END: changeCartItem controllers");
		return ApiResponse.success(
			HttpStatus.CREATED.value(), 
			"Successfully updated cart item", 
			cartDto
		);
	}

	@PatchMapping
	public ApiResponse<CartDto> updateCart(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@RequestBody UpdateCartDto updateCartDto
	) {
		log.info("START: updateCart controllers");
		CartDto cartDto = cartService.updateCart(userPrincipal.getId(), updateCartDto);
		
		log.info("END: updateCart controllers");
		return ApiResponse.success(
			"Successfully updated cart", 
			cartDto
		);
	}
}
