package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.enums.Role;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	interface AuthView {
		UUID getAccountId();
		String getEmail();
		String getPasswordHash();
		Role getRole();
	}
	
	Optional<AuthView> findByEmail(String email);
}
