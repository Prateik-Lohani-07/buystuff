package com.buystuff.buystuff_api.dto.product;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProductDto {
	@NotNull
	private UUID productId;
	
	@Size(min = 1)
	private String name;

	@PositiveOrZero
	private Double price;
	
	@DecimalMin(value = "0.0", inclusive = true)
	private Double discount;
	
	@PositiveOrZero
	private Integer stock;
	
	@Size(min = 100, max = 800)
	private String description;

	private Boolean isActive;
		
	private List<String> categories;
}
