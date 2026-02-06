package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.address.AddressDto;
import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
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
	public PaymentInfoDto addPaymentInfo(Account account, CreatePaymentInfoDto createPaymentInfoDto) {
		log.info("START: addPaymentInfo service");
		
		PaymentInfo paymentInfo = PaymentInfoMapper.toEntity(createPaymentInfoDto, account);
		paymentInfo = paymentInfoRepository.save(paymentInfo);

		PaymentInfoDto paymentInfoDto = PaymentInfoMapper.toDTO(paymentInfo);

		log.info("END: addPaymentInfo service");
		return paymentInfoDto;
	}

	@Override
	public AddressDto addAddress(Account account, CreateAddressDto createAddressDto) {
		log.info("START: addAddress service");
		
		Address address = AddressMapper.toEntity(createAddressDto, account);
		address = addressRepository.save(address);

		AddressDto addressDto = AddressMapper.toDto(address);
		
		log.info("END: addAddress service");
		return addressDto;
	}

	@Override
	public AddressDto updateAddress(Account account, UpdateAddressDto updateAddressDto) {
		log.info("START: updateAddress service");
		
		Address address = 
			addressRepository
				.findById(updateAddressDto.getAddressId())
				.orElseThrow(() -> new NotFoundException("Address not found."));
		
		AddressMapper.updateEntity(updateAddressDto, address);
		address = addressRepository.save(address);

		AddressDto addressDto = AddressMapper.toDto(address);

		log.info("END: updateAddress service");

		return addressDto;
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
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
