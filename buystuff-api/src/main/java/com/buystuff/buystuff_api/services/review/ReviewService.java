package com.buystuff.buystuff_api.services.review;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.product.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.product.review.ReviewDto;
import com.buystuff.buystuff_api.dto.product.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;

public interface ReviewService {
	ReviewDto addReview(UUID productId, UUID accountId, CreateReviewDto createReviewDto);
	ReviewDto editReview(UUID productId, UUID accountId, UUID reviewId, UpdateReviewDto updateReviewDto);
	void deleteReview(UUID productId, UUID accountId, UUID reviewId);
}
