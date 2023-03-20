package com.slcube.shop.business.order.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.item.repository.ItemRepositoryHelper;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.order.domain.OrderItem;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderItemResponseDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import com.slcube.shop.business.order.repository.OrderItemRepository;
import com.slcube.shop.business.order.repository.OrderRepository;
import com.slcube.shop.business.order.repository.OrderRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final ItemRepositoryHelper itemRepositoryHelper;

    @Override
    @Transactional
    public Long order(List<OrderCreateRequestDto> requestDtoList, MemberSessionDto sessionDto) {
        Order order = Order.createOrder(sessionDto.getMemberId(), requestDtoList);

        requestDtoList.forEach(requestDto -> {
                    Item item = itemRepositoryHelper.findByNotDeleted(requestDto.getItemId());
                    item.decreaseStockQuantity(requestDto.getQuantity());
                });

        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
    @Transactional
    public Long cancelOrder(Long orderId) {
        Order order = orderRepositoryHelper.findById(orderId);

        order.cancelOrder();

        orderItemRepository.findByOrderId(orderId).forEach(orderItem -> {
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

    @Override
    public OrderResponseDto findOrder(MemberSessionDto sessionDto, Long orderId) {
        Order order = orderRepositoryHelper.findById(orderId);

        List<Long> itemIds = order.getOrderItems().stream().map(OrderItem::getItemId).toList();
        Map<Long, Item> itemMap = itemRepository.findByIdIn(itemIds)
                .stream().collect(Collectors.toMap(Item::getId, item -> item));


        List<OrderItemResponseDto> orderItems = order.getOrderItems().stream().map(orderItem -> {
            Item item = itemMap.get(orderItem.getItemId());
            return new OrderItemResponseDto(orderItem, item);
        }).toList();
        return new OrderResponseDto(order, orderItems);
    }
}
