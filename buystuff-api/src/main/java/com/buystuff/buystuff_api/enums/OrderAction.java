package com.buystuff.buystuff_api.enums;

public enum OrderAction {
	PAY, // CUSTOMER
	SHIP, // FULFILLMENT MANAGER
	DELIVER, // FULFILLMENT MANAGER
	CANCEL, // CUSTOMER
	RETURN, // CUSTOMER
	RETURN_PERIOD_OVER; // SYSTEM
}
