package com.buystuff.buystuff_api.validators.order_status;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AllowedOrderStatusValidator.class)
public @interface AllowOrderStatus {
	public String message() default "Invalid order status passed: Should be one of SHIPPED | DELIVERED | RETURNED";	

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
