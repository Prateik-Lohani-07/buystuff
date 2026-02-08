package com.buystuff.buystuff_api.dto.cart;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UpdateCartDto {
	private UUID accountId;

	
}
