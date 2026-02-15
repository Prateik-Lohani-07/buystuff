package com.buystuff.buystuff_api.validators.order_status;

import java.util.Set;

import com.buystuff.buystuff_api.enums.OrderStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AllowedOrderStatusValidator implements ConstraintValidator<AllowOrderStatus, OrderStatus> {

	private static final Set<OrderStatus> ALLOWED = Set.of(
		OrderStatus.SHIPPED,
		OrderStatus.DELIVERED,
		OrderStatus.RETURNED
	);

	@Override
	public boolean isValid(OrderStatus status, ConstraintValidatorContext constraintValidatorContext) {
		return ALLOWED.contains(status);
	}
}