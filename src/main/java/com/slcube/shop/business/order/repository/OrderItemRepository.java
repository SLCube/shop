package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi from OrderItem oi inner join oi.order where oi.order.memberId = :memberId")
    Page<OrderItem> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
