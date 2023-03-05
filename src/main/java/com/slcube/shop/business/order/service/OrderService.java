package com.slcube.shop.business.order.service;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void order(List<OrderCreateRequestDto> requestDtoList, MemberSessionDto sessionDto);
    Long cancelOrder(Long orderId);
    Page<OrderListResponseDto> getOrders();
}
