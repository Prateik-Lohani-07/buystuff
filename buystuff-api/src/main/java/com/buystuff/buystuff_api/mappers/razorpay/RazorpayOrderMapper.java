package com.buystuff.buystuff_api.mappers.razorpay;

import java.util.UUID;

import org.hibernate.type.descriptor.java.ObjectArrayJavaType;
import org.json.JSONObject;

import com.buystuff.buystuff_api.dto.razorpay.RazorpayOrderDto;
import com.razorpay.Order;

public abstract class RazorpayOrderMapper {
	public static RazorpayOrderDto toDto(Order order) {
		RazorpayOrderDto orderDto = new RazorpayOrderDto();
		
		orderDto.setId(order.get("id"));

		JSONObject notes = (JSONObject) order.get("notes");
		UUID orderId = UUID.fromString((String) (notes.get("order_id")));
		orderDto.setOrderId(orderId);
		
		orderDto.setStatus(order.get("status"));

		Number amountVal = order.get("amount");
		Double amount = amountVal.doubleValue() / 100.0;
		orderDto.setAmount(amount);
		orderDto.setCurrency(order.get("currency"));

		return orderDto;
	}
}
