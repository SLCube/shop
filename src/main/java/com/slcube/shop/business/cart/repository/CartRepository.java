package com.slcube.shop.business.cart.repository;

import com.slcube.shop.business.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>, CartQueryRepository {

    @Query("select c from Cart c where c.id = :cartId and c.isDeleted = false")
    public Optional<Cart> findByNotDeleted(@Param("cartId") Long cartId);
}
