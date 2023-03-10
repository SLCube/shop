package com.slcube.shop.business.order.domain;

import com.slcube.shop.business.delivery.domain.Delivery;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.common.exception.OrderAlreadyCancelException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @CreatedDate
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER;

    @Column(insertable = false)
    private LocalDateTime orderCancelDate;

    private Long memberId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    private Delivery delivery;

    private Order(Long memberId) {
        this.memberId = memberId;
    }

    public static Order createOrder(Long memberId, List<OrderCreateRequestDto> requestDtoList) {
        Order order = new Order(memberId);
        requestDtoList.stream()
                .forEach(requestDto -> {
                    OrderItem orderItem = new OrderItem(requestDto.getItemId(), requestDto.getQuantity());
                    order.getOrderItems().add(orderItem);
                    orderItem.addOrder(order);
                });

        return order;
    }

    public void cancelOrder() {
        if (this.orderStatus == OrderStatus.CANCEL) {
            throw new OrderAlreadyCancelException();
        }

        this.orderStatus = OrderStatus.CANCEL;
    }
}
