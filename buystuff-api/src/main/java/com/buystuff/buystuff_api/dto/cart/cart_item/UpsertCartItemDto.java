package com.buystuff.buystuff_api.dto.cart.cart_item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpsertCartItemDto (
	@NotBlank String productCode,
	@NotNull @Min(value = 1) Integer quantity
) {}
