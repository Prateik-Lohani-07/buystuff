package com.buystuff.buystuff_api.dto.review;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto extends BaseResponseDto {
	@NotBlank 
	private String reviewId;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer rating;
	
	@NotBlank
	private String title;
	
	@NotBlank 
	private String content;
	
	@NotNull
	private Boolean verified;

	@NotBlank
	private String productId;

	@NotBlank
	private String accountId;
}
