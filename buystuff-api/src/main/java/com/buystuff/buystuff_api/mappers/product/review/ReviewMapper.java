package com.buystuff.buystuff_api.mappers.product.review;

import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Review;

public abstract class ReviewMapper {
	public static ReviewDto toDto(Review entity) {
		ReviewDto dto = new ReviewDto();

		dto.setReviewId(entity.getReviewId());
		dto.setRating(entity.getRating());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setVerified(entity.getVerified());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	public static Review toEntity(Account account, CreateReviewDto dto) {
		Review entity = new Review();

		entity.setAccount(account);
		entity.setRating(dto.getRating());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());

		return entity;
	}
}
