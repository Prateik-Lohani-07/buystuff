package com.buystuff.buystuff_api.mappers.order;

import com.buystuff.buystuff_api.mappers.order.order_item.OrderItemMapper;
import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.entities.Order;

public abstract class OrderMapper {
	public static OrderDto toDTO(Order entity) {
		OrderDto dto = new OrderDto();

		dto.setOrderId(entity.getOrderId());
		
		dto.setItems(
			entity.getItems()
				.stream()
				.map(OrderItemMapper::toDTO)
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
