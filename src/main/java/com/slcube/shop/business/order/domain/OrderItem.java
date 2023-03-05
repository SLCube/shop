package com.slcube.shop.business.order.domain;

import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    private void addOrder(Order order) {
        this.order = order;
    }

    public static List<OrderItem> createOrderItem(Long memberId, List<OrderCreateRequestDto> requestDtoList) {
        Order order = new Order(memberId);

        List<OrderItem> orderItems = requestDtoList.stream()
                .map(requestDto -> {
                    OrderItem orderItem = new OrderItem(requestDto.getItemId(), requestDto.getQuantity());
                    orderItem.addOrder(order);
                    return orderItem;
                }).collect(Collectors.toList());

        return orderItems;
    }
}
