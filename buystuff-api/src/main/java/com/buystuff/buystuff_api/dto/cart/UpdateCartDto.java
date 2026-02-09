package com.buystuff.buystuff_api.dto.cart;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UpdateCartDto {
	@NotNull
	private UUID accountId;

	@Valid
	private List<UpsertCartItemDto> items;

	private UUID couponId;
}
