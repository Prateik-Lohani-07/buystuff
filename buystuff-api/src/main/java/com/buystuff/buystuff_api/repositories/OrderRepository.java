package com.buystuff.buystuff_api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
	Optional<Order> findByOrderIdAndAccount_AccountId(UUID orderId, UUID accountId);
	Page<Order> findByAccount(Account account, Pageable pageable);
}
