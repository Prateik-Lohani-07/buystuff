package com.buystuff.buystuff_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
	
}
