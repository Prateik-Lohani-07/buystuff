package com.buystuff.buystuff_api.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

public class UserPrincipal implements UserDetails {
	@Getter
	private final Account account;

	public UserPrincipal(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return account.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return account.getEmail();
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String toString() {
		String accountId = account.getAccountId().toString(), email = account.getEmail(), passwordHash = account.getPasswordHash();
		String s = String.format("Account={id=%s, email=%s, password_hash=%s}", accountId, email, passwordHash);
		return s;
	}
}
