package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
	Optional<Coupon> findByCouponCode(String couponCode);

	@Query(value = """
		SELECT c
		FROM Coupon c
		WHERE (:coupon_code IS NULL OR c.couponCode LIKE %:coupon_code%)
	""")
	Page<Coupon> findAll(
		@Param("coupon_code") String couponCode, 
		Pageable pageable
	);
}
