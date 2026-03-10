package com.buystuff.buystuff_api.dto.cart.coupon;

import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Size;

public record CouponFilterDto(
	Integer size,
	Integer page,
	@Size(min = 1) String sortBy,
	String couponCode,
	Sort.Direction sortOrder
) { }