package com.buystuff.buystuff_api.dto.cart;

import java.util.Collections;
import java.util.List;

import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public record UpdateCartDto (
	@Valid List<UpsertCartItemDto> items,
	@Size(min = 1) String couponCode
) {
	public UpdateCartDto {
        if (items == null) items = Collections.emptyList();
    }
}
