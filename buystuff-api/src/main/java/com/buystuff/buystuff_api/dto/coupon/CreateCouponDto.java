package com.buystuff.buystuff_api.dto.coupon;

import com.buystuff.buystuff_api.enums.DiscountType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCouponDto {
	@NotBlank private String couponCode;
	@NotBlank private String name;
	@DecimalMin(value = "0.0") private Double discountValue;
	@NotNull private DiscountType discountType;
}
