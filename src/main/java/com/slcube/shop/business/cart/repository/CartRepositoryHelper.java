package com.slcube.shop.business.cart.repository;

import com.slcube.shop.business.cart.domain.Cart;
import com.slcube.shop.common.exception.CartItemNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CartRepositoryHelper {

    public Cart findByDeleted(CartRepository cartRepository, Long cartId) {
        return cartRepository.findByNotDeleted(cartId)
                .orElseThrow(() -> new CartItemNotFoundException());
    }
}
