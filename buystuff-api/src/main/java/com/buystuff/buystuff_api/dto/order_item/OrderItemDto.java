package com.buystuff.buystuff_api.dto.order_item;

import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.snapshots.ProductSnapshot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto extends BaseResponseDto {
	@Valid
	private ProductSnapshot productSnapshot;

	@NotNull
	private UUID productId;

	@NotNull
	@DecimalMin(value = "0.0")
	private Double price;

	@NotNull
	@Min(value = 0)
	private Integer quantity;
}
