package com.buystuff.buystuff_api.dto.payment_info.credit_card;

import java.time.LocalDateTime;

import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCreditCardDto extends CreatePaymentInfoDto {
	private String cardNumber;
	private LocalDateTime expiryDate;
	private String cardHolder;

	public CreateCreditCardDto() {
		super(PaymentType.CREDIT_CARD);
	}

	public String getLast4DigitsOfCardNumber() {
		int len = cardNumber.length();
		return this.cardNumber.substring(len-4, len);
	}
}
