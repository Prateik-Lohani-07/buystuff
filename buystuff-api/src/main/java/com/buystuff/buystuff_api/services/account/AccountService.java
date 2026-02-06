package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.address.AddressDto;
import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;

public interface AccountService {
	PaymentInfoDto addPaymentInfo(Account account, CreatePaymentInfoDto createPaymentInfoDto);
	AddressDto addAddress(Account account, CreateAddressDto createAddressDto);
	AddressDto updateAddress(Account account, UpdateAddressDto updateAddressDto);
	Account getAccount(UUID accountId);
	Account getAccountByEmail(String email);
    void saveAccount(Account account);
}
