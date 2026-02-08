package com.buystuff.buystuff_api.dto.product.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReviewDto {
	@NotNull
	@Min(1)
	@Max(5)
	private Integer rating;

	@NotBlank
	private String title;

	@NotBlank 
	private String content;
}
