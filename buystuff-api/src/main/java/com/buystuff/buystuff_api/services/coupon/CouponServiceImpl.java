package com.buystuff.buystuff_api.services.coupon;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.entities.Coupon;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.repositories.CouponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	private final CouponRepository couponRepository;

    @Override
    public Coupon getCoupon(UUID couponId) {
		log.info("START: getCoupon service");
		
		Coupon coupon = 
			couponRepository.findById(couponId)
			.orElseThrow(() -> new NotFoundException(String.format("Coupon with ID %s not found", couponId.toString())));
		
		log.info("END: getCoupon service");
		return coupon;
    }

    @Override
    public Coupon getCouponByCouponCode(String couponCode) {
		log.info("START: getCouponByCouponCode service");

		Coupon coupon = 
			couponRepository.findByCouponCode(couponCode)
			.orElseThrow(() -> new NotFoundException(String.format("Coupon with coupon code %s not found", couponCode)));

		log.info("END: getCouponByCouponCode service");
		return coupon;
    }
	
}
