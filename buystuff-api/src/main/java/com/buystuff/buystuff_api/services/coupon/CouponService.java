package com.buystuff.buystuff_api.services.coupon;

import java.util.UUID;

import com.buystuff.buystuff_api.entities.Coupon;

public interface CouponService {
	Coupon getCoupon(UUID couponId);
	Coupon getCouponByCouponCode(String couponCode);
}
