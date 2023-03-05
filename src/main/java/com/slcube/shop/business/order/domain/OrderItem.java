package com.slcube.shop.business.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long itemId;

    private int quantity;

    @Builder
    private OrderItem(Long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    protected void addOrder(Order order) {
        this.order = order;
    }

    public static OrderItem createOrderItem(Long itemId, int quantity, Long memberId) {
        OrderItem orderItem = new OrderItem(itemId, quantity);
        Order order = new Order(memberId);
        orderItem.addOrder(order);

        return orderItem;
    }
}
