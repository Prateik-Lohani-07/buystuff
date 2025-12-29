package com.buystuff.buystuff_api.converters.payment_info.upi;

import com.buystuff.buystuff_api.dto.payment_info.upi.CreateUPIDto;
import com.buystuff.buystuff_api.dto.payment_info.upi.UPIDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.UPIPaymentInfo;

public abstract class UPIConverter {
	public static UPIPaymentInfo convertTo(CreateUPIDto dto, Account account) {
		UPIPaymentInfo entity = new UPIPaymentInfo();
		
		entity.setAccount(account);
		entity.setUpiId(dto.getUpiId());

		return entity;
	}

	public static UPIDto convertFrom(UPIPaymentInfo entity) {
		UPIDto dto = new UPIDto();

		dto.setPaymentInfoId(entity.getPaymentInfoId());
		
		dto.setAccountId(entity.getAccount().getAccountId());
		dto.setUpiId(entity.getUpiId());
		dto.setActive(entity.isActive());

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
