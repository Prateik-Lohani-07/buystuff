package com.buystuff.buystuff_api.dto.cart.cart_item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpsertCartItemDto (
	@NotBlank(message = "product code must not be blank or null") 
	String productCode,

	@NotNull(message = "quantity must not be null") 
	@Min(value = 1, message = "quantity must be >= 1") 
	Integer quantity
) {}
