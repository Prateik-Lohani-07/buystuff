package com.buystuff.buystuff_api.converters.order;

import com.buystuff.buystuff_api.converters.order.order_item.OrderItemConverter;
import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.entities.Order;

public abstract class OrderConverter {
	public static OrderDto toDTO(Order entity) {
		OrderDto dto = new OrderDto();

		dto.setOrderId(entity.getOrderId());
		
		dto.setItems(
			entity.getItems()
				.stream()
				.map(OrderItemConverter::toDTO)
				.toList()
		);
		
		dto.setAccountId(entity.getAccount().getAccountId());
		dto.setDeliveredAt(entity.getDeliveredAt());
		dto.setDiscount(entity.getDiscount());
		dto.setStatus(entity.getStatus());
		dto.setPaymentInfoSnapshot(entity.getPaymentInfoSnapshot());
		
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());


		return dto;
	}
}
