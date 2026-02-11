package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
	Optional<Coupon> findByCouponCode(String couponCode);
}
