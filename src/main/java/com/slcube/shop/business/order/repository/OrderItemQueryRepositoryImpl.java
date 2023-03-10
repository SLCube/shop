package com.slcube.shop.business.order.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.order.dto.OrderItemResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.slcube.shop.business.item.domain.QItem.item;
import static com.slcube.shop.business.order.domain.QOrderItem.orderItem;

@RequiredArgsConstructor
public class OrderItemQueryRepositoryImpl implements OrderItemQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<OrderItemResponseDto> findDtoByOrderId(Long orderId) {
        return query
                .select(Projections.fields(OrderItemResponseDto.class,
                        orderItem.id.as("orderItemId"),
                        item.itemName,
                        item.price.as("itemPrice"),
                        orderItem.quantity))
                .from(orderItem)
                .innerJoin(item).on(orderItem.itemId.eq(item.id))
                .where(orderItem.order.id.eq(orderId))
                .fetch();
    }
}
