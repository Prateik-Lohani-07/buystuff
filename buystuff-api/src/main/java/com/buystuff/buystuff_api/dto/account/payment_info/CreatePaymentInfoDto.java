package com.buystuff.buystuff_api.dto.account.payment_info;

import java.util.UUID;

import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CreatePaymentInfoDto {
	private UUID accountId;
	private PaymentType paymentType;

	public CreatePaymentInfoDto(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
