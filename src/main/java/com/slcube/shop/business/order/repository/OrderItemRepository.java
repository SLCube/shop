package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemQueryRepository {

    @Query("select oi from OrderItem oi where oi.order.id = :orderId")
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
}
