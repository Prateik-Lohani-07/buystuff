package com.buystuff.buystuff_api.dto.account.payment_info;

import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class PaymentInfoDto extends BaseResponseDto {
	private UUID paymentInfoId;
	private PaymentType paymentType;
	private UUID accountId;
	private boolean isActive;

	public PaymentInfoDto(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
