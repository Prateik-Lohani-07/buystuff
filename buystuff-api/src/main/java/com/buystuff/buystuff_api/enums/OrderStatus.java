package com.buystuff.buystuff_api.enums;

import java.util.Map;

public enum OrderStatus {
	// initiated by the system
	PENDING_PAYMENT,
	PLACED,
	ACCEPTED,

	// initiated by admin or user
	CANCELLED,

	// handled by the fulfillment manager
	SHIPPED,
	DELIVERED,
	RETURNED;

	private static final Map<OrderStatus, Map<OrderAction, OrderStatus>> TRANSITION_MAP = Map.of(
		PENDING_PAYMENT, Map.of(
			OrderAction.PAY, PLACED,
			OrderAction.CANCEL, CANCELLED
		),
		PLACED, Map.of(
			OrderAction.SHIP, SHIPPED,
			OrderAction.CANCEL, CANCELLED
		),	
		SHIPPED, Map.of(
			OrderAction.DELIVER, DELIVERED,
			OrderAction.CANCEL, CANCELLED
		),
		DELIVERED, Map.of(
			OrderAction.RETURN_PERIOD_OVER, ACCEPTED,
			OrderAction.RETURN, RETURNED
		)
	);

	public OrderStatus next(OrderAction action) throws IllegalArgumentException {
		if (!TRANSITION_MAP.containsKey(this)) {
			throw new IllegalArgumentException(
				String.format("No state transitions available on status: %s", this.toString())
			);
		}
			
		var possibleTransitions = TRANSITION_MAP.get(this);

		if (!possibleTransitions.containsKey(action)) {
			throw new IllegalArgumentException(
				String.format("Invalid event %s on status: %s", action.toString(), this.toString())
			);
		}

		return possibleTransitions.get(action);
	}
}
