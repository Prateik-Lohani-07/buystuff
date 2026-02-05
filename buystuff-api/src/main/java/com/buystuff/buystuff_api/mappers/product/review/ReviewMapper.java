package com.buystuff.buystuff_api.mappers.product.review;

import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Product;
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

	public static Review toEntity(CreateReviewDto dto, Account account, Product product) {
		Review entity = new Review();

		entity.setAccount(account);
		entity.setProduct(product);
		entity.setRating(dto.getRating());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setVerified(false);

		return entity;
	}

	public static void updateEntity(UpdateReviewDto dto, Review entity) {
		if (dto.getContent() != null) {
			entity.setContent(dto.getContent());
		}
		
		if (dto.getRating() != null) {
			entity.setRating(dto.getRating());
		}

		if (dto.getTitle() != null) {
			entity.setTitle(dto.getTitle());
		}
	}
}
