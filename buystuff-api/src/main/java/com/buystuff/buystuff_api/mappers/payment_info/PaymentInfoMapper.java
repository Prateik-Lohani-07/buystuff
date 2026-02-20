package com.buystuff.buystuff_api.mappers.payment_info;

import com.buystuff.buystuff_api.mappers.payment_info.credit_card.CreditCardMapper;
import com.buystuff.buystuff_api.mappers.payment_info.upi.UPIMapper;
import com.buystuff.buystuff_api.snapshots.PaymentInfoSnapshot;
import com.buystuff.buystuff_api.dto.account.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.account.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.dto.account.payment_info.credit_card.CreateCreditCardDto;
import com.buystuff.buystuff_api.dto.account.payment_info.upi.CreateUPIDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.CreditCardPaymentInfo;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.entities.UPIPaymentInfo;

public abstract class PaymentInfoMapper {

	public static PaymentInfo toEntity(CreatePaymentInfoDto dto, Account account) {
		switch (dto.getPaymentType()) {
			case CREDIT_CARD -> {
				return CreditCardMapper.toEntity((CreateCreditCardDto) dto, account);
			}
			case UPI -> {
				return UPIMapper.toEntity((CreateUPIDto) dto, account);
			}
			default -> throw new IllegalArgumentException(
				"Unsupported payment type: " + dto.getPaymentType()
			);
		}
	}

	public static PaymentInfoDto toDto(PaymentInfo entity) {
		if (entity instanceof CreditCardPaymentInfo ccEntity)
			return CreditCardMapper.toDto(ccEntity);
		
		else if (entity instanceof UPIPaymentInfo upiEntity)
			return UPIMapper.toDto(upiEntity);

		else throw new IllegalArgumentException("Unsupported payment type in entity");
	}

	public static PaymentInfoSnapshot toSnapshot(PaymentInfo entity) {
		if (entity instanceof CreditCardPaymentInfo ccEntity)
			return CreditCardMapper.toSnapshot(ccEntity);
		
		else if (entity instanceof UPIPaymentInfo upiEntity)
			return UPIMapper.toSnapshot(upiEntity);

		else throw new IllegalArgumentException("Unsupported payment type in entity");
	}
}
