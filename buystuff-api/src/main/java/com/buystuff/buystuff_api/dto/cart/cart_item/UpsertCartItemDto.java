package com.buystuff.buystuff_api.dto.cart.cart_item;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpsertCartItemDto (
	@NotNull UUID productId,
	@NotNull @Min(value = 1) Integer quantity
) {}
