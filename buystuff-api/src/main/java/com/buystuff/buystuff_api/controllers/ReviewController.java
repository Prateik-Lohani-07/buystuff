package com.buystuff.buystuff_api.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.product.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.product.review.ReviewDto;
import com.buystuff.buystuff_api.dto.product.review.UpdateReviewDto;
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

	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/")
	public ApiResponse<ReviewDto> addReview(
		@PathVariable(name = "product_id") UUID productId,
		@RequestBody CreateReviewDto createReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: addReview controller");
		
		ReviewDto review = reviewService.addReview(productId, userPrincipal.getId(), createReviewDto);
		
		log.info("END: addReview controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(),
			"Successfully added review", 
			review
		);
	}

	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('CUSTOMER')")
	@PatchMapping("/{review_id}")
	public ApiResponse<ReviewDto> editReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@RequestBody UpdateReviewDto updateReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: editReview controller");
		
		ReviewDto review = reviewService.editReview(productId, userPrincipal.getId(), reviewId, updateReviewDto);

		log.info("END: editReview controller");
		return ApiResponse.success("Successfully edited review", review);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('CUSTOMER')")
	@DeleteMapping("/{review_id}")
	public ApiResponse<Void> deleteReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: deleteReview controller");
		
		reviewService.deleteReview(productId, userPrincipal.getId(), reviewId);

		log.info("END: deleteReview controller");
		return ApiResponse.success(
			HttpStatus.NO_CONTENT.value(),
			"Successfully deleted review", 
			null
		);
	}
}
