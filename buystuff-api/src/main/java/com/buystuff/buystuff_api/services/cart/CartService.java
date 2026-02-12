package com.buystuff.buystuff_api.services.cart;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.cart.CartDto;
import com.buystuff.buystuff_api.dto.cart.UpdateCartDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpdateCartItemDto;
import com.buystuff.buystuff_api.dto.cart.cart_item.UpsertCartItemDto;

public interface CartService {
	CartDto viewCart(UUID accountId);
	CartDto addAllToCart(UUID accountId, List<UpsertCartItemDto> upsertItems);
	CartDto changeCartItem(UUID accountId, UUID itemId, UpdateCartItemDto updateCartItemDto);
	CartDto updateCart(UUID accountId, UpdateCartDto updateCartDto);
	void clearCart(UUID accountId);
}
