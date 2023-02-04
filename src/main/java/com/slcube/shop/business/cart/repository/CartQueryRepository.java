package com.slcube.shop.business.cart.repository;

import com.slcube.shop.business.cart.domain.Cart;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartQueryRepository {

    public List<Cart> findAllCarts(Pageable pageable);
}
