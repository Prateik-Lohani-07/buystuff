package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.account.AccountDto;
import com.buystuff.buystuff_api.dto.account.address.AddressDto;
import com.buystuff.buystuff_api.dto.account.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.account.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.account.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.account.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.account.AccountMapper;
import com.buystuff.buystuff_api.mappers.address.AddressMapper;
import com.buystuff.buystuff_api.mappers.payment_info.PaymentInfoMapper;
import com.buystuff.buystuff_api.repositories.AccountRepository;
import com.buystuff.buystuff_api.repositories.AddressRepository;
import com.buystuff.buystuff_api.repositories.PaymentInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;
	private final AddressRepository addressRepository;
	private final PaymentInfoRepository paymentInfoRepository;
	
	@Override
	public PaymentInfoDto addPaymentInfo(UUID accountId, CreatePaymentInfoDto createPaymentInfoDto) {
		log.info("START: addPaymentInfo service");
		
		Account account = getAccount(accountId);
		PaymentInfo paymentInfo = PaymentInfoMapper.toEntity(createPaymentInfoDto, account);
		paymentInfo = paymentInfoRepository.save(paymentInfo);

		PaymentInfoDto paymentInfoDto = PaymentInfoMapper.toDto(paymentInfo);

		log.info("END: addPaymentInfo service");
		return paymentInfoDto;
	}

	@Override
	public PaymentInfo getPaymentInfo(UUID paymentInfoId) {
		log.info("START: getPaymentInfo service");

		PaymentInfo paymentInfo = paymentInfoRepository
			.findById(paymentInfoId)
			.orElseThrow(() -> new NotFoundException(
				String.format("PaymentInfo for ID %s not found", paymentInfoId.toString())
			));
		
		log.info("END: getPaymentInfo service");
		return paymentInfo;
	}

	@Override
	public AddressDto addAddress(UUID accountId, CreateAddressDto createAddressDto) {
		log.info("START: addAddress service");
		
		Account account = getAccount(accountId);
		Address address = AddressMapper.toEntity(createAddressDto, account);
		address = addressRepository.save(address);

		AddressDto addressDto = AddressMapper.toDto(address);
		
		log.info("END: addAddress service");
		return addressDto;
	}

	@Override
	public AddressDto updateAddress(UUID accountId, UUID addressId, UpdateAddressDto updateAddressDto) {
		log.info("START: updateAddress service");
		
		Address address = 
			addressRepository
				.findByAddressIdAndAccount_AccountId(addressId, accountId)
				.orElseThrow(() -> new NotFoundException("Address not found."));
		
		AddressMapper.updateEntity(updateAddressDto, address);
		address = addressRepository.save(address);

		AddressDto addressDto = AddressMapper.toDto(address);

		log.info("END: updateAddress service");
		return addressDto;
	}

	@Override
	public Address getAddress(UUID addressId) {
		log.info("START: getAddress service");
		
		Address address = 
			addressRepository
				.findById(addressId)
				.orElseThrow(() -> new NotFoundException("Address not found."));
		
		log.info("END: getAddress service");
		return address;
	}

	@Override
	public Account getAccount(UUID accountId) {
		Account account = accountRepository
			.findById(accountId)
			.orElseThrow(() -> new NotFoundException("Account not found."));

		return account;
	}

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

	@Override
	public AccountDto getAccountDetails(UUID accountId) {
		log.info("START: getAccountDetails service");
		
		Account account = getAccount(accountId);
		AccountDto accountDto = AccountMapper.toDto(account);
		
		log.info("END: getAccountDetails service");
		return accountDto;
	}
}
