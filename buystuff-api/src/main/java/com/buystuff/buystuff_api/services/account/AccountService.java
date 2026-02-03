package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;

public interface AccountService {
	void addPaymentInfo(Account account, CreatePaymentInfoDto createPaymentInfoDto);
	Address addAddress(Account account, CreateAddressDto createAddressDto);
	void updateAddress(Account account, UpdateAddressDto updateAddressDto);
	Account getAccount(UUID accountId);
	Account getAccountByEmail(String email);
    Account saveAccount(Account account);
}
