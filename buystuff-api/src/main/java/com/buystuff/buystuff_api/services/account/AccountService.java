package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;

public interface AccountService {
	void addToCart(UUID accountId, UUID productId);
	void addPaymentInfo(UUID accountId, CreatePaymentInfoDto createPaymentInfoDto);
	void addAddress(UUID accountId, CreateAddressDto createAddressDto);
	void updateAddress(UUID accountId, UpdateAddressDto updateAddressDto);
}
