package com.buystuff.buystuff_api.converters.order.order_item;

import com.buystuff.buystuff_api.dto.order_item.OrderItemDto;
import com.buystuff.buystuff_api.entities.OrderItem;


public abstract class OrderItemConverter {
	public static OrderItemDto toDTO(OrderItem entity) {
		OrderItemDto dto = new OrderItemDto();

		dto.setProductSnapshot(entity.getProductSnapshot());
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		dto.setProductId(entity.getProduct().getProductId());
		
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
