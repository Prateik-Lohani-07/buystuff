package com.buystuff.buystuff_api.dto.razorpay;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RazorpayOrderDto {
	private String id;

	@PositiveOrZero
	private Double amount;

	private String currency;

	@NotNull
	private UUID orderId;
	
	private String status;
}
