package com.buystuff.buystuff_api.dto.cart.cart_item;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDto {
	@NotNull
	private Long itemId;

	@DecimalMin(value = "0.0")
	private double price;

	@Min(value = 0)
	private Integer quantity;
	
	@NotBlank
	private String productCode;
}
