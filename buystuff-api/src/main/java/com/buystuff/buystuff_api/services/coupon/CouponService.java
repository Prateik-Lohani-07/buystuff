package com.buystuff.buystuff_api.services.coupon;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.cart.coupon.CouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CouponFilterDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CreateCouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.UpdateCouponDto;
import com.buystuff.buystuff_api.entities.Coupon;

public interface CouponService {
	Coupon getCoupon(UUID couponId);
	Coupon getCouponByCouponCode(String couponCode);

	List<CouponDto> getAllCoupons(CouponFilterDto filters);
	CouponDto addCoupon(CreateCouponDto createCouponDto);
	CouponDto editCoupon(UUID couponId, UpdateCouponDto updateCouponDto);
	void deleteCoupon(UUID couponId);
}
