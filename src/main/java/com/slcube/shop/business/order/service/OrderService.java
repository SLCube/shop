package com.slcube.shop.business.order.service;

import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Long createOrder(OrderCreateRequestDto requestDto);
    public Long cancelOrder(Long orderId);
    public Page<OrderListResponseDto> getOrders();
}
