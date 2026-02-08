package com.buystuff.buystuff_api.mappers.cart;

import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.mappers.cart.cart_items.CartItemMapper;
import com.buystuff.buystuff_api.mappers.coupon.CouponMapper;

public abstract class CartMapper {
	public static CartDto toDto(Cart entity) {
		CartDto dto = new CartDto();

		dto.setAccountId(entity.getAccountId());
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
