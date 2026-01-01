package com.buystuff.buystuff_api.dto.cart;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.cart_item.CartItemDto;
import com.buystuff.buystuff_api.dto.coupon.CouponDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDto {
	@NotNull
	private UUID cartId;

	@NotNull
	private UUID accountId;
	
	@Valid
	private CouponDto coupon;
	
	@Valid
	private List<CartItemDto> items;
}
