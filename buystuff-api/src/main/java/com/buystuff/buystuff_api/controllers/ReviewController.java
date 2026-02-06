package com.buystuff.buystuff_api.controllers;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.review.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products/{product_id}/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/")
	public ApiResponse<ReviewDto> addReview(
		@PathVariable(name = "product_id") UUID productId,
		@RequestBody CreateReviewDto createReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: addReview controller");
		
		ReviewDto review = reviewService.addReview(productId, userPrincipal.getAccount(), createReviewDto);
		
		log.info("END: addReview controller");
		return ApiResponse.success("Successfully added review", review);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PatchMapping("/{review_id}")
	public ApiResponse<ReviewDto> editReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@RequestBody UpdateReviewDto updateReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: editReview controller");
		
		ReviewDto review = reviewService.editReview(productId, userPrincipal.getAccount(), reviewId, updateReviewDto);

		log.info("END: editReview controller");
		return ApiResponse.success("Successfully edited review", review);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@DeleteMapping("/{review_id}")
	public ApiResponse<Void> deleteReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: deleteReview controller");
		
		reviewService.deleteReview(productId, userPrincipal.getAccount(), reviewId);

		log.info("END: deleteReview controller");
		return ApiResponse.success("Successfully deleted review", null);
	}
}
