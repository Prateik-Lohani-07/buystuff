package com.buystuff.buystuff_api.mappers.coupon;

import com.buystuff.buystuff_api.dto.cart.coupon.CouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CreateCouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.UpdateCouponDto;
import com.buystuff.buystuff_api.entities.Coupon;

public abstract class CouponMapper {
	public static CouponDto toDto(Coupon entity) {
		CouponDto dto = new CouponDto();

		dto.setCouponId(entity.getCouponId());
		dto.setCouponCode(entity.getCouponCode());
		dto.setName(entity.getName());
		dto.setDiscountValue(entity.getDiscountValue());
		dto.setDiscountType(entity.getDiscountType());

		return dto;
	}

	public static Coupon toEntity(CreateCouponDto dto) {
		Coupon entity = new Coupon();

		entity.setCouponCode(dto.getCouponCode());
		entity.setName(dto.getName());
		entity.setDiscountValue(dto.getDiscountValue());
		entity.setDiscountType(dto.getDiscountType());

		return entity;
	}

	public static void updateEntity(UpdateCouponDto dto, Coupon entity) {
		if (dto.getName() != null) 
			entity.setName(dto.getName());

		if (dto.getDiscountValue() != null) 
			entity.setDiscountValue(dto.getDiscountValue());

		if (dto.getDiscountType() != null) 
			entity.setDiscountType(dto.getDiscountType());
	}
}
