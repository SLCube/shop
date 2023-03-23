package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.common.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRepositoryHelper {

    private final OrderRepository orderRepository;

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }
}
