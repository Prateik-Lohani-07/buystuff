package com.buystuff.buystuff_api.snapshots;

import java.util.UUID;

import com.buystuff.buystuff_api.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME, 
	include = JsonTypeInfo.As.PROPERTY, 
	property = "payment_type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CreditCardPaymentInfoSnapshot.class, name = "CREDIT_CARD"),
	@JsonSubTypes.Type(value = UPIPaymentInfoSnapshot.class, name = "UPI")
})
public abstract class PaymentInfoSnapshot {
	private UUID paymentInfoId;
	private UUID accountId;
	private PaymentType paymentType;
}
