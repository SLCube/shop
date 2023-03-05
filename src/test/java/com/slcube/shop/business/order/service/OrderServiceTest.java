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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.util.ReflectionTestUtils.setField;

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
    @DisplayName("주문 테스트")
    void orderTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);

        List<OrderCreateRequestDto> requestDtoList = new ArrayList<>();

        for (long i = 1; i <= 3; i++) {
            OrderCreateRequestDto requestDto = new OrderCreateRequestDto();
            setField(requestDto, "itemId", i);
            setField(requestDto, "quantity", (int) (2 * i));

            requestDtoList.add(requestDto);
        }

        orderService.order(requestDtoList, sessionDto);

        Long orderId = 1L;
        Optional<Order> order = orderRepository.findById(orderId);

        assertAll(
                () -> assertThat(order.isPresent()).isEqualTo(true),
                () -> assertThat(order.get().getMemberId()).isEqualTo(sessionDto.getMemberId()),
                () -> assertThat(order.get().getOrderStatus()).isEqualTo(OrderStatus.ORDER)
        );

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        for (long i = 1; i <= 3; i++) {
            OrderItem orderItem = orderItems.get((int) (i - 1));
            assertThat(orderItem.getOrder()).isEqualTo(order.get());
            assertThat(orderItem.getItemId()).isEqualTo(i);
            assertThat(orderItem.getQuantity()).isEqualTo((int) i * 2);
        }
    }
}