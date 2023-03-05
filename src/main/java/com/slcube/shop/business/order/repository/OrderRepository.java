package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
