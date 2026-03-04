package com.buystuff.buystuff_api.dto.account.payment_info;

import com.buystuff.buystuff_api.dto.account.payment_info.credit_card.CreateCreditCardDto;
import com.buystuff.buystuff_api.dto.account.payment_info.upi.CreateUPIDto;
import com.buystuff.buystuff_api.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "payment_type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CreateCreditCardDto.class, name = "CREDIT_CARD"),
	@JsonSubTypes.Type(value = CreateUPIDto.class, name = "UPI")
})
@Getter @Setter
public abstract class CreatePaymentInfoDto {
	private PaymentType paymentType;

	public CreatePaymentInfoDto(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
