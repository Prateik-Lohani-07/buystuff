package com.buystuff.buystuff_api.services.coupon;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.cart.coupon.CouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CouponFilterDto;
import com.buystuff.buystuff_api.dto.cart.coupon.CreateCouponDto;
import com.buystuff.buystuff_api.dto.cart.coupon.UpdateCouponDto;
import com.buystuff.buystuff_api.entities.Coupon;
import com.buystuff.buystuff_api.exceptions.BadRequestException;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.coupon.CouponMapper;
import com.buystuff.buystuff_api.repositories.CouponRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	private final CouponRepository couponRepository;
	private final List<String> sortByFields = List.of("updated_at");

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

	@Override
	public List<CouponDto> getAllCoupons(CouponFilterDto filters) {
		log.info("START: getAllCoupons service");

		int page = filters.page();
		int pageSize = filters.size();
		String couponCode = filters.couponCode();

		if (pageSize < 0) {
			throw new BadRequestException("Limit must be greater than 0");
		}

		Sort sortOptions;
		String sortBy = filters.sortBy();
		Sort.Direction sortOrder = filters.sortOrder();

		if (sortBy != null) {
			if (!validSortBy(sortBy)) throw new BadRequestException("Invalid sort by field");
			if (sortBy.equals("updated_at")) sortBy = "updatedAt";

			sortOptions = Sort.by(sortBy);

			if (sortOrder == null || sortOrder.equals(Sort.Direction.ASC))
				sortOptions = sortOptions.ascending();
			else 
				sortOptions = sortOptions.descending();
		}
		else sortOptions = Sort.by("updatedAt").descending();

		PageRequest pageRequest = PageRequest.of(
			page, 
			pageSize,
			sortOptions
		);

		Page<Coupon> coupons = couponRepository.findAll(couponCode, pageRequest);
		List<CouponDto> couponDtos = 
			coupons.stream()
				.map(CouponMapper::toDto)
				.toList();

		log.info("END: getAllCoupons service");
		return couponDtos;
	}

	@Override
	@Transactional
	public CouponDto addCoupon(CreateCouponDto createCouponDto) {
		log.info("START: addCoupon service");

		Coupon coupon = CouponMapper.toEntity(createCouponDto);
		coupon = couponRepository.save(coupon);
		CouponDto couponDto = CouponMapper.toDto(coupon);

		log.info("END: addCoupon service");
		return couponDto;
	}

	@Override
	@Transactional
	public CouponDto editCoupon(UUID couponId, UpdateCouponDto updateCouponDto) {
		log.info("START: editCoupon service");

		Coupon coupon = getCoupon(couponId);
		CouponMapper.updateEntity(updateCouponDto, coupon);
		coupon = couponRepository.save(coupon);
		CouponDto couponDto = CouponMapper.toDto(coupon);

		log.info("END: editCoupon service");
		return couponDto;
	}

	private boolean validSortBy(String fieldName) {
		return sortByFields.contains(fieldName);
	}
	
}
