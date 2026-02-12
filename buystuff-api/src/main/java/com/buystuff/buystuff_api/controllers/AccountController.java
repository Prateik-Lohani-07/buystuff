package com.buystuff.buystuff_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.services.account.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;

	
}
