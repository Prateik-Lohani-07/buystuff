package com.buystuff.buystuff_api.converters.payment_info;

import com.buystuff.buystuff_api.converters.payment_info.credit_card.CreditCardConverter;
import com.buystuff.buystuff_api.converters.payment_info.upi.UPIConverter;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.dto.payment_info.credit_card.CreateCreditCardDto;
import com.buystuff.buystuff_api.dto.payment_info.upi.CreateUPIDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.CreditCardPaymentInfo;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.entities.UPIPaymentInfo;

public abstract class PaymentInfoConverter {

	public static PaymentInfo toEntity(CreatePaymentInfoDto dto, Account account) {
		switch (dto.getPaymentType()) {
			case CREDIT_CARD -> {
				return CreditCardConverter.toEntity((CreateCreditCardDto) dto, account);
			}
			case UPI -> {
				return UPIConverter.toEntity((CreateUPIDto) dto, account);
			}
			default -> throw new IllegalArgumentException(
				"Unsupported payment type: " + dto.getPaymentType()
			);
		}
	}

	public static PaymentInfoDto toDTO(PaymentInfo entity) {
		if (entity instanceof CreditCardPaymentInfo ccEntity)
			return CreditCardConverter.toDTO(ccEntity);
		
		else if (entity instanceof UPIPaymentInfo upiEntity)
			return UPIConverter.toDTO(upiEntity);

		else throw new IllegalArgumentException("Unsupported payment type in entity");
	}
}
