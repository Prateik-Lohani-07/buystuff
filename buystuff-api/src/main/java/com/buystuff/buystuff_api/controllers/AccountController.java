package com.buystuff.buystuff_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.account.address.AddressDto;
import com.buystuff.buystuff_api.dto.account.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.account.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.dto.account.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.account.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;

	@PostMapping("/addresses")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<AddressDto> addAddress(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@Valid @RequestBody CreateAddressDto createAddressDto
	) {
		log.info("START: addAddress controller");

		AddressDto addressDto = accountService.addAddress(userPrincipal.getId(), createAddressDto);

		log.info("END: addAddress controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(),
			"Successfully added address",
			addressDto
		);
	}

	@PostMapping("/payment-info")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<PaymentInfoDto> addPaymentInfo(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@Valid @RequestBody CreatePaymentInfoDto createPaymentInfoDto
	) {
		log.info("START: addPaymentInfo controller");

		PaymentInfoDto paymentInfoDto = accountService.addPaymentInfo(userPrincipal.getId(), createPaymentInfoDto);

		log.info("END: addPaymentInfo controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(),
			"Successfully added payment information",
			paymentInfoDto
		);
	}
	
}
