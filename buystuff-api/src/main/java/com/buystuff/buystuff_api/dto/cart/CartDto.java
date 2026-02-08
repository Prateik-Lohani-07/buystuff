package com.buystuff.buystuff_api.dto.cart;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.CartItemDto;
import com.buystuff.buystuff_api.dto.coupon.CouponDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartDto extends BaseResponseDto {
	@NotNull
	private UUID accountId;
	
	@Valid
	private CouponDto coupon;
	
	@Valid
	private List<CartItemDto> items;
}
