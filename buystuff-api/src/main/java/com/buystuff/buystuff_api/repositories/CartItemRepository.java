package com.buystuff.buystuff_api.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartAndProduct_ProductCodeIn(Cart cart, Collection<String> productCodes);
	void deleteByCart(Cart cart);
}
