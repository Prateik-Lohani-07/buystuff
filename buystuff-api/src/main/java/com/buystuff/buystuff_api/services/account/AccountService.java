package com.buystuff.buystuff_api.services.account;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.account.AccountDto;
import com.buystuff.buystuff_api.dto.account.address.AddressDto;
import com.buystuff.buystuff_api.dto.account.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.account.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.account.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.account.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.PaymentInfo;

public interface AccountService {
	PaymentInfoDto addPaymentInfo(UUID accountId, CreatePaymentInfoDto createPaymentInfoDto);
	PaymentInfo getPaymentInfo(UUID paymentInfoId);


	List<AddressDto> getAllAddresses(UUID accountId);
	AddressDto addAddress(UUID accountId, CreateAddressDto createAddressDto);
	AddressDto getAddressDetails(UUID accountId, UUID addressId);
	Address getAddress(UUID accountId, UUID addressId);
	AddressDto updateAddress(UUID accountId, UUID addressId, UpdateAddressDto updateAddressDto);
	
	Account getAccount(UUID accountId);
	AccountDto getAccountDetails(UUID accountId);
    void saveAccount(Account account);
}
