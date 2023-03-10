package com.slcube.shop.business.order.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepositoryHelper;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.domain.Order;
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
    private final ItemRepositoryHelper itemRepositoryHelper;

    @Override
    @Transactional
    public Long order(List<OrderCreateRequestDto> requestDtoList, MemberSessionDto sessionDto) {
        Order order = Order.createOrder(sessionDto.getMemberId(), requestDtoList);
        Order savedOrder = orderRepository.save(order);

        requestDtoList.stream()
                .forEach(requestDto -> {
                    Item item = itemRepositoryHelper.findByNotDeleted(requestDto.getItemId());
                    item.decreaseStockQuantity(requestDto.getQuantity());
                });

        return savedOrder.getId();
    }

    @Override
    @Transactional
    public Long cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());

        order.cancelOrder();

        orderItemRepository.findByOrderId(orderId).stream()
                .forEach(orderItem -> {
                    Item item = itemRepositoryHelper.findByNotDeleted(orderItem.getItemId());
                    item.increaseStockQuantity(orderItem.getQuantity());
                });

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
