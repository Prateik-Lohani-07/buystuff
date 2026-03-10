package com.buystuff.buystuff_api.dto.cart.coupon;

import com.buystuff.buystuff_api.enums.DiscountType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCouponDto {
	@Size(min = 1) 
	private String name;
	
	@DecimalMin(value = "0.0") 
	private Double discountValue;
	
	private DiscountType discountType;
}
