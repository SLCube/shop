package com.slcube.shop.business.cart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.slcube.shop.business.cart.domain.QCart.*;

@RequiredArgsConstructor
public class CartQueryRepositoryImpl implements  CartQueryRepository{

    private final JPAQueryFactory query;

    @Override
    public List<Cart> findAllCarts(Pageable pageable) {
        return query.selectFrom(cart)
                .where(cart.isDeleted.eq(Boolean.FALSE))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
