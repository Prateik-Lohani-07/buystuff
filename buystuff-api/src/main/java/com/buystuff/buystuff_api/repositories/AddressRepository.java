package com.buystuff.buystuff_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {
	
}
