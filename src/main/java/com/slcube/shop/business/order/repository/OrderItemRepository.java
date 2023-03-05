package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
