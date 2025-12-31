package com.buystuff.buystuff_api.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.dto.order_items.CreateOrderItemDto;
import com.buystuff.buystuff_api.enums.OrderStatus;
import com.buystuff.buystuff_api.snapshots.AddressSnapshot;
import com.buystuff.buystuff_api.snapshots.PaymentInfoSnapshot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto extends BaseResponseDto {
	@NotNull
	private UUID orderId;

	@DecimalMin(value = "0.0")
	private Double discount;
	
	@Valid
	@NotEmpty
	private List<CreateOrderItemDto> items;
	
	@Valid
	@NotNull
	private AddressSnapshot shippingAddressSnapshot;
	
	private LocalDateTime deliveredAt;

	@NotNull
	private OrderStatus status;
	
	@Valid
	@NotNull
	private PaymentInfoSnapshot paymentInfoSnapshot;
	
	@NotNull
	private UUID accountId;
}
