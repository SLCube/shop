package com.slcube.shop.business.order.domain;

import com.slcube.shop.business.delivery.domain.Delivery;
import com.slcube.shop.common.exception.OrderAlreadyCancelException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    private Delivery delivery;

    protected Order(Long memberId) {
        this.memberId = memberId;
    }

    public void cancelOrder() {
        if (this.orderStatus == OrderStatus.CANCEL) {
            throw new OrderAlreadyCancelException();
        }

        this.orderStatus = OrderStatus.CANCEL;
    }
}
