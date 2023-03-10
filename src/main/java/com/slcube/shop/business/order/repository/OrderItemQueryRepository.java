package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.dto.OrderItemResponseDto;

import java.util.List;

public interface OrderItemQueryRepository {
    List<OrderItemResponseDto> findDtoByOrderId(Long orderId);
}
