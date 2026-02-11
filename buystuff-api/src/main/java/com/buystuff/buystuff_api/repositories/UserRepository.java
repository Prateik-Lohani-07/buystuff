package com.buystuff.buystuff_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
