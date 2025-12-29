package com.buystuff.buystuff_api.dto.payment_info.credit_card;

import java.time.LocalDateTime;

import com.buystuff.buystuff_api.dto.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreditCardDto extends PaymentInfoDto {
	private String cardNumberLast4Digits;
	private LocalDateTime expiryDate;
	private String cardHolder;

	public CreditCardDto() {
		super(PaymentType.CREDIT_CARD);
	}
}
