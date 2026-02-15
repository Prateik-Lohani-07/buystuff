package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
	Optional<Coupon> findByCouponCode(String couponCode);
}
