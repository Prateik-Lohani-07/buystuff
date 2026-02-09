package com.buystuff.buystuff_api.dto.cart.cart_item;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpsertCartItemDto {
	private UUID itemId;
	private Integer quantity;
}
