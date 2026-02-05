package com.buystuff.buystuff_api.services.user_details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = 
			accountRepository
				.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Account not found."));

		return new UserPrincipal(account);
	}
}
