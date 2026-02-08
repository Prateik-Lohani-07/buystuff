package com.buystuff.buystuff_api.mappers.coupon;

import com.buystuff.buystuff_api.dto.coupon.CouponDto;
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
}
