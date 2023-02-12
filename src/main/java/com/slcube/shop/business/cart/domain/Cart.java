package com.slcube.shop.business.cart.domain;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.config.jpa.BooleanToYnConverter;
import com.slcube.shop.common.domain.BaseEntity;
import com.slcube.shop.business.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
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
    private int quantity;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    protected Cart() {
        this.quantity = 1;
    }

    private Cart(Item item, int quantity, Member member) {
        this.item = item;
        this.quantity = quantity;
        this.member = member;
    }

    public static Cart createCartItem(Item item, int quantity, Member member) {
        Cart cart = new Cart(item, quantity, member);
        return cart;
    }

    public void deleteCartItem() {
        this.isDeleted = Boolean.TRUE;
    }

    public void updateCartItem(int quantity) {
        this.quantity = quantity;
    }
}
