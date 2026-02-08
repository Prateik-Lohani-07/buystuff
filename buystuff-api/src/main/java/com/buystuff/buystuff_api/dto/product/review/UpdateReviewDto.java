package com.buystuff.buystuff_api.dto.product.review;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateReviewDto {
	@NotNull
	private UUID reviewId;

	private Integer rating;
	private String title;
	private String content;
}
