package com.slcube.shop.business.order.repository;

import com.slcube.shop.business.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.memberId = :memberId")
    Page<Order> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
