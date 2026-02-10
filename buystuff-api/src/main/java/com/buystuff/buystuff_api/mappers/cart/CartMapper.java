package com.buystuff.buystuff_api.mappers.cart;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.mappers.cart.cart_items.CartItemMapper;
import com.buystuff.buystuff_api.mappers.coupon.CouponMapper;

public abstract class CartMapper {
	public static CartDto toDto(Cart entity, UUID accountId) {
		CartDto dto = new CartDto();

		dto.setAccountId(accountId);
		dto.setCoupon(CouponMapper.toDto(entity.getCoupon()));
		dto.setItems(
			entity.getItems()
				.stream()
				.map(CartItemMapper::toDto)
				.toList()
		);
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	} 
}
