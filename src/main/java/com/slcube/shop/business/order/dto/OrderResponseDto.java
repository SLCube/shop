package com.slcube.shop.business.order.dto;

import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.order.domain.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private List<OrderItemResponseDto> orderItems;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    public OrderResponseDto(Order order, List<OrderItemResponseDto> orderItems) {
        orderId = order.getId();
        orderStatus = order.getOrderStatus();
        orderDate = order.getOrderDate();
        this.orderItems = orderItems;
    }
}
