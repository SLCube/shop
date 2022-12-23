package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.order.OrderCreateRequestDto;
import com.slcube.shop.business.dto.order.OrderListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Long createOrder(OrderCreateRequestDto requestDto);
    public Long cancelOrder(Long id);
    public Page<OrderListResponseDto> getOrders();
}
