package com.buystuff.buystuff_api.services.account;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.address.UpdateAddressDto;
import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.address.AddressMapper;
import com.buystuff.buystuff_api.mappers.payment_info.PaymentInfoMapper;
import com.buystuff.buystuff_api.repositories.AccountRepository;
import com.buystuff.buystuff_api.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void addToCart(UUID accountId, UUID productId) {
		Account account = getAccount(accountId);
			
		Product product = productRepository
			.findById(productId)
			.orElseThrow(() -> new NotFoundException("Product not found."));
			
		account.addToCart(product);
	}
	
	@Override
	public void addPaymentInfo(UUID accountId, CreatePaymentInfoDto createPaymentInfoDto) {
		Account account = getAccount(accountId);

		PaymentInfo paymentInfo = PaymentInfoMapper.toEntity(createPaymentInfoDto, account);
		account.addPaymentInfo(paymentInfo);
	}

	@Override
	public void addAddress(UUID accountId, CreateAddressDto createAddressDto) {
		Account account = getAccount(accountId);
	
		Address address = AddressMapper.toEntity(createAddressDto, account);
		account.addAddress(address);
	}

	@Override
	public void updateAddress(UUID accountId, UpdateAddressDto updateAddressDto) {
		Account account = getAccount(accountId);
		Address address = account.getAddresses()
			.stream()
			.filter(a -> a.getAddressId().equals(updateAddressDto.getAddressId()))
			.findFirst()
			.orElseThrow(() -> new NotFoundException("Address not found"));
	
		AddressMapper.updateEntity(updateAddressDto, address);
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
