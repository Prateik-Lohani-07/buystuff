package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.address.AddressMapper;
import com.buystuff.buystuff_api.mappers.payment_info.PaymentInfoMapper;
import com.buystuff.buystuff_api.repositories.AccountRepository;
import com.buystuff.buystuff_api.repositories.AddressRepository;
import com.buystuff.buystuff_api.repositories.PaymentInfoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final AddressRepository addressRepository;
	private final PaymentInfoRepository paymentInfoRepository;

	public AccountServiceImpl(
		AccountRepository accountRepository, 
		AddressRepository addressRepository,
		PaymentInfoRepository paymentInfoRepository
	) {
		this.accountRepository = accountRepository;
		this.addressRepository = addressRepository;
		this.paymentInfoRepository = paymentInfoRepository;
	}
	
	@Override
	public void addPaymentInfo(Account account, CreatePaymentInfoDto createPaymentInfoDto) {
		PaymentInfo paymentInfo = PaymentInfoMapper.toEntity(createPaymentInfoDto, account);
		paymentInfoRepository.save(paymentInfo);
	}

	@Override
	public Address addAddress(Account account, CreateAddressDto createAddressDto) {
		Address address = AddressMapper.toEntity(createAddressDto, account);
		address = addressRepository.save(address);
		return address;
	}

	@Override
	public void updateAddress(Account account, UpdateAddressDto updateAddressDto) {
		Address address = addressRepository.findById(updateAddressDto.getAddressId())
			.orElseThrow(() -> new NotFoundException("Address not found."));

		AddressMapper.updateEntity(updateAddressDto, address);
		addressRepository.save(address);
	}

	@Override
	public Account getAccount(UUID accountId) {
		Account account = accountRepository
			.findById(accountId)
			.orElseThrow(() -> new NotFoundException("Account not found."));

		return account;
	}

	@Override
	public Account getAccountByEmail(String email) {
		Account account = accountRepository
			.findByEmail(email)
			.orElseThrow(() -> new NotFoundException("Account not found."));

        return account;
	}

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}
