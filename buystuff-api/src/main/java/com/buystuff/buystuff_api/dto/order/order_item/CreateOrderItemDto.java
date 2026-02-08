package com.buystuff.buystuff_api.dto.order.order_item;

import com.buystuff.buystuff_api.snapshots.ProductSnapshot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderItemDto {
	@Valid
	private ProductSnapshot productSnapshot;
	
	@NotNull
	private String productCode;
	
	@NotNull
	@DecimalMin(value = "0.0")
	private Double price;
	
	@NotNull
	@Min(value = 0)
	private Integer quantity;
}
