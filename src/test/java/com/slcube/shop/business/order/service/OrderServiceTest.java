package com.slcube.shop.business.order.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.order.domain.OrderItem;
import com.slcube.shop.business.order.domain.OrderStatus;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.repository.OrderItemRepository;
import com.slcube.shop.business.order.repository.OrderRepository;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
@WithMockMember
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        MemberSessionDto sessionDto = (MemberSessionDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        member = Member.builder()
                .email(sessionDto.getLoginEmail())
                .username(sessionDto.getUsername())
                .password("test password")
                .build();

        memberRepository.save(member);
    }

    @Test
    void orderTest() {
        OrderCreateRequestDto requestDto = new OrderCreateRequestDto();
        MemberSessionDto sessionDto = new MemberSessionDto(member);
        ReflectionTestUtils.setField(requestDto, "itemId", 1L);
        ReflectionTestUtils.setField(requestDto, "quantity", 10);

        Long orderItemId = orderService.order(requestDto, sessionDto);

        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);

        assertAll(
                () -> assertThat(orderItem.isPresent()).isEqualTo(true),
                () -> assertThat(orderItem.get().getItemId()).isEqualTo(1L),
                () -> assertThat(orderItem.get().getQuantity()).isEqualTo(10)
        );

        Order order = orderItem.get().getOrder();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
    }
}