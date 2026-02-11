package com.buystuff.buystuff_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
