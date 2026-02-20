package com.buystuff.buystuff_api.services.order;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.dto.order.OrderFilterDto;
import com.buystuff.buystuff_api.enums.OrderAction;
import com.buystuff.buystuff_api.enums.Role;

public interface OrderService {
	/**
	 * Creates a new order in PENDING_PAYMENT state. 
	 * To move to PLACED state, user must pay the respective amount.
	 */
	OrderDto createOrder(UUID accountId, UUID addressId, UUID paymentInfoId); 

	/**
	 * Returns past orders placed by an account. Return value, by default, is sorted
	 * on updated_at in descending order. 
	 */
	List<OrderDto> viewOrderHistory(UUID accountId, OrderFilterDto filters);

	OrderDto getOrder(UUID accountId, UUID orderId);
	void updateOrderStatus(UUID accountId, Role role, UUID orderId, OrderAction action);
}
