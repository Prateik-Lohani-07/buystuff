package com.buystuff.buystuff_api.services.review;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;

public interface ReviewService {
	ReviewDto addReview(UUID productId, Account account, CreateReviewDto createReviewDto);
	void editReview(UUID productId, Account account, UUID reviewId, UpdateReviewDto updateReviewDto);
	void deleteReview(UUID productId, Account account, UUID reviewId);
}
