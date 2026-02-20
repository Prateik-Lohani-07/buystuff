package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
	Optional<Address> findByAddressIdAndAccount_AccountId(UUID addressId, UUID accountId);
}
