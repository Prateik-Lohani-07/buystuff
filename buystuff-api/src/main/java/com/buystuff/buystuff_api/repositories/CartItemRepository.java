package com.buystuff.buystuff_api.repositories;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
	List<CartItem> findByCartAndProduct_IdIn(Cart cart, Collection<UUID> productIds);
	void deleteByCart(Cart cart);
}
