package com.buystuff.buystuff_api.dto.product;

import java.util.List;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto extends BaseResponseDto {
	@NotBlank
	private String productId;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String productCode;
	
	@PositiveOrZero
	private Double price;
	
	@DecimalMin(value = "0.0", inclusive = true)
	private Double discount;
	
	@PositiveOrZero
	private Integer stock;
	
	@NotBlank
	@Size(min = 100, max = 800)
	private String description;
	
	@Valid
	private List<ReviewDto> reviews;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "5.0", inclusive = true)
	private Double avgRating;
	
	@NotNull
	private Boolean isActive;
	
	@NotNull
	@Size(min = 1)
	private List<String> categories;
}

