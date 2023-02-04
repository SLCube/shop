package com.slcube.shop.business.cart.domain;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.domain.BaseEntity;
import com.slcube.shop.business.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private int quantity = 1;

    public static Cart createCartItem(Item item, int quantity) {
        Cart cart = new Cart();
        cart.item = item;
        cart.quantity = quantity;
        return cart;
    }
}
