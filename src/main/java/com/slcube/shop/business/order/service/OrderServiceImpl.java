package com.slcube.shop.business.order.service;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.domain.OrderItem;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderListResponseDto;
import com.slcube.shop.business.order.repository.OrderItemRepository;
import com.slcube.shop.business.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Long order(OrderCreateRequestDto requestDto, MemberSessionDto sessionDto) {
        OrderItem orderItem = OrderItem.createOrderItem(requestDto.getItemId(), requestDto.getQuantity(), sessionDto.getMemberId());
        return orderItemRepository.save(orderItem).getItemId();
    }

    @Override
    public Long cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public Page<OrderListResponseDto> getOrders() {
        return null;
    }
}
