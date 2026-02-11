package com.buystuff.buystuff_api.dto.cart.cart_item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCartItemDto(
	@NotNull @Size(min = 0) Integer quantity
) {}
