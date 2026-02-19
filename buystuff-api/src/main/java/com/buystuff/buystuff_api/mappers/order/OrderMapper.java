package com.buystuff.buystuff_api.mappers.order;

import java.time.LocalDateTime;

import com.buystuff.buystuff_api.mappers.order.order_item.OrderItemMapper;
import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.CartItem;
import com.buystuff.buystuff_api.entities.Order;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.enums.OrderStatus;
import com.buystuff.buystuff_api.mappers.address.AddressMapper;
import com.buystuff.buystuff_api.mappers.payment_info.PaymentInfoMapper;

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

	public static Order toEntity(Account account, Address address, PaymentInfo paymentInfo, Cart cart) {
		Order order = new Order();

		if (cart.getCoupon() != null) {
			Double totalCartAmount = 
				cart.getItems()
					.stream()
					.mapToDouble(CartItem::getTotalCost)
					.sum();

			Double discountAmount = cart.getCoupon().getDiscountAmount(totalCartAmount);

			order.setDiscount(discountAmount);
		}
		order.setItems(
			cart.getItems()
				.stream()
				.map(OrderItemMapper::toEntity)
				.toList()
		);
		order.setStatus(OrderStatus.PENDING_PAYMENT);
		order.setShippingAddressSnapshot(AddressMapper.toSnapshot(address));
		order.setPaymentInfoSnapshot(PaymentInfoMapper.toSnapshot(paymentInfo));
		order.setAccount(account);

		return order;
	}

	public static void updateEntity(Order entity, OrderStatus status, LocalDateTime deliveredAt) {
		if (status != null) entity.setStatus(status);
		if (deliveredAt != null) entity.setDeliveredAt(deliveredAt);
	}
}
