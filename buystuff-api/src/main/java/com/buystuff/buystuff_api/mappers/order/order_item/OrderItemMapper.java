package com.buystuff.buystuff_api.mappers.order.order_item;

import com.buystuff.buystuff_api.dto.order.order_item.OrderItemDto;
import com.buystuff.buystuff_api.entities.CartItem;
import com.buystuff.buystuff_api.entities.Order;
import com.buystuff.buystuff_api.entities.OrderItem;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.mappers.product.ProductMapper;


public abstract class OrderItemMapper {
	public static OrderItemDto toDto(OrderItem entity) {
		OrderItemDto dto = new OrderItemDto();

		dto.setProductSnapshot(entity.getProductSnapshot());
		dto.setPrice(entity.getProductSnapshot().getPrice());
		dto.setQuantity(entity.getQuantity());

		Product product = entity.getProduct(); 
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getName());

		return dto;
	}

	public static OrderItem toEntity(CartItem cartItem, Order order) {
		OrderItem orderItem = new OrderItem();
		
		orderItem.setOrder(order);
		orderItem.setQuantity(cartItem.getQuantity());
		
		Product product = cartItem.getProduct();
		orderItem.setProduct(product);
		orderItem.setProductSnapshot(ProductMapper.toSnapshot(product));

		return orderItem;
	}
}
