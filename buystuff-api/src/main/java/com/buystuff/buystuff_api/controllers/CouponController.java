package com.buystuff.buystuff_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.cart.coupon.CouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CouponFilterDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CreateCouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.UpdateCouponDto;
import com.buystuff.buystuff_api.services.coupon.CouponService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
	private final CouponService couponService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<List<CouponDto>> getAllCoupons(
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(name = "sort_by", required = false) String sortBy,
		@RequestParam(name = "sort_order", required = false) Sort.Direction sortOrder,
		@RequestParam(name = "coupon_code", required = false) String couponCode
	) {
		log.info("START: getAllCoupons controller");

		CouponFilterDto couponFilterDto = new CouponFilterDto(size, page, sortBy, couponCode, sortOrder);
		List<CouponDto> couponDtos = couponService.getAllCoupons(couponFilterDto);

		log.info("END: getAllCoupons controller");
		return ApiResponse.success(
			"Successfully fetched all coupons", 
			couponDtos
		);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<CouponDto> addCoupon(
		@Valid @RequestBody CreateCouponDto createCouponDto
	) {
		log.info("START: addCoupon controller");

		CouponDto couponDto = couponService.addCoupon(createCouponDto);

		log.info("END: addCoupon controller");
		return ApiResponse.success(
			"Successfully added coupon", 
			couponDto
		);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PatchMapping("/{coupon_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<CouponDto> editCoupon(
		@PathVariable(name = "coupon_id") UUID couponId,
		@Valid @RequestBody UpdateCouponDto updateCouponDto
	) {
		log.info("START: editCoupon controller");

		CouponDto couponDto = couponService.editCoupon(couponId, updateCouponDto);

		log.info("END: editCoupon controller");
		return ApiResponse.success(
			"Successfully edited coupon", 
			couponDto
		);
	}
	
}
