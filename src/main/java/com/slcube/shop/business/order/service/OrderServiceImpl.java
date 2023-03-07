package com.slcube.shop.business.order.service;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.order.domain.OrderItem;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderItemResponseDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import com.slcube.shop.business.order.repository.OrderItemRepository;
import com.slcube.shop.business.order.repository.OrderRepository;
import com.slcube.shop.common.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void order(List<OrderCreateRequestDto> requestDtoList, MemberSessionDto sessionDto) {
        List<OrderItem> orderItems = OrderItem.createOrderItem(sessionDto.getMemberId(), requestDtoList);
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    @Transactional
    public Long cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());

        order.cancelOrder();
        return order.getId();
    }

    @Override
    public Page<OrderResponseDto> findOrders(MemberSessionDto sessionDto, Pageable pageable) {
        Page<Order> orders = orderRepository.findByMemberId(sessionDto.getMemberId(), pageable);
        List<OrderResponseDto> result = orders.stream()
                .map(order -> {
                    List<OrderItemResponseDto> orderItems = orderItemRepository.findDtoByOrderId(order.getId());
                    return new OrderResponseDto(order, orderItems);
                }).collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}
