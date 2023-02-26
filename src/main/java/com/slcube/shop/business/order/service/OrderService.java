package com.slcube.shop.business.order.service;

import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Long createOrder(OrderCreateRequestDto requestDto);
    Long cancelOrder(Long orderId);
    Page<OrderListResponseDto> getOrders();
}
