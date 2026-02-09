package com.buystuff.buystuff_api.dto.cart.coupon;

import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.enums.DiscountType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CouponDto extends BaseResponseDto {
	@NotNull private UUID couponId;
	@NotBlank private String couponCode;
	@NotBlank private String name;
	@DecimalMin(value = "0.0") private Double discountValue;
	@NotNull private DiscountType discountType;
}
