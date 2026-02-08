package com.buystuff.buystuff_api.mappers.payment_info.credit_card;

import com.buystuff.buystuff_api.dto.account.payment_info.credit_card.CreateCreditCardDto;
import com.buystuff.buystuff_api.dto.account.payment_info.credit_card.CreditCardDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.CreditCardPaymentInfo;

public abstract class CreditCardMapper {
	public static CreditCardPaymentInfo toEntity(CreateCreditCardDto dto, Account account) {
		CreditCardPaymentInfo entity = new CreditCardPaymentInfo();
		
		entity.setAccount(account);
		entity.setCardNumberLast4Digits(dto.getLast4DigitsOfCardNumber());
		entity.setCardHolder(dto.getCardHolder());
		entity.setExpiryDate(dto.getExpiryDate());

		return entity;
	}

	public static CreditCardDto toDTO(CreditCardPaymentInfo entity) {
		CreditCardDto dto = new CreditCardDto();

		dto.setPaymentInfoId(entity.getPaymentInfoId());
		
		dto.setAccountId(entity.getAccount().getAccountId());
		dto.setCardNumberLast4Digits(entity.getCardNumberLast4Digits());
		dto.setCardHolder(entity.getCardHolder());
		dto.setExpiryDate(entity.getExpiryDate());
		dto.setActive(entity.isActive());

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
