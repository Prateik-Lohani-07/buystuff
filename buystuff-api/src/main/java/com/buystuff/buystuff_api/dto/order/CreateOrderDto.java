package com.buystuff.buystuff_api.dto.order;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.order.order_item.CreateOrderItemDto;
import com.buystuff.buystuff_api.snapshots.AddressSnapshot;
import com.buystuff.buystuff_api.snapshots.PaymentInfoSnapshot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderDto {
	@DecimalMin(value = "0.0")
	private Double discount;
	
	@Valid
	@NotEmpty
	private List<CreateOrderItemDto> items;
	
	@Valid
	private AddressSnapshot shippingAddressSnapshot;

	@Valid
	private PaymentInfoSnapshot paymentInfoSnapshot;
	
	@NotNull
	private UUID accountId;
}
