package com.buystuff.buystuff_api.mappers.cart.cart_items;

import com.buystuff.buystuff_api.dto.cart.cart_item.CartItemDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;
import com.buystuff.buystuff_api.entities.CartItem;

public abstract class CartItemMapper {
	public static CartItemDto toDto(CartItem entity) {
		CartItemDto dto = new CartItemDto();

		dto.setItemId(entity.getItemId());
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		dto.setProductCode(entity.getProduct().getProductCode());

		return dto;
	}

	public static void updateEntity(UpsertCartItemDto dto, CartItem entity) {
		// entity
	}
}
