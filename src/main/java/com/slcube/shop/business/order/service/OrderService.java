package com.slcube.shop.business.order.service;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Long order(List<OrderCreateRequestDto> requestDtoList, MemberSessionDto sessionDto);
    Long cancelOrder(Long orderId);
    Page<OrderResponseDto> findOrders(MemberSessionDto sessionDto, Pageable pageable);

    OrderResponseDto findOrder(MemberSessionDto sessionDto, Long orderId);
}
