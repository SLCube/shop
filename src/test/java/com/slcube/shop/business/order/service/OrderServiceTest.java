package com.slcube.shop.business.order.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.order.domain.OrderItem;
import com.slcube.shop.business.order.domain.OrderStatus;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderItemResponseDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import com.slcube.shop.business.order.repository.OrderItemRepository;
import com.slcube.shop.business.order.repository.OrderRepository;
import com.slcube.shop.common.exception.NotEnoughStockQuantity;
import com.slcube.shop.common.exception.OrderAlreadyCancelException;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@SpringBootTest
@Transactional
@WithMockMember
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        createMember();
        createItem();
    }

    private void createMember() {
        MemberSessionDto sessionDto = (MemberSessionDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        member = Member.builder()
                .email(sessionDto.getLoginEmail())
                .username(sessionDto.getUsername())
                .password("test password")
                .build();

        memberRepository.save(member);
    }

    private void createItem() {
        for (int i = 1; i <= 3 ; i++) {
            Item item = Item.builder()
                    .price(10000 * i)
                    .stockQuantity(10 * i)
                    .itemName("test item name " + i)
                    .build();
            itemRepository.save(item);
        }
    }


    @Test
    @DisplayName("주문 테스트")
    void orderTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);

        Long orderId = createOrder(sessionDto);

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

        List<Item> items = itemRepository.findAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            int remainedStockQuantity = 10 * (i + 1) - 2 * (i + 1);

            assertThat(item.getStockQuantity()).isEqualTo(remainedStockQuantity);
        }
    }

    @Test
    @DisplayName("주문 취소")
    void cancelOrderTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);

        Long orderId = createOrder(sessionDto);

        Long cancelOrderId = orderService.cancelOrder(orderId);

        Optional<Order> order = orderRepository.findById(cancelOrderId);

        assertAll(
                () -> assertThat(order.isPresent()).isEqualTo(true),
                () -> assertThat(order.get().getOrderStatus()).isEqualTo(OrderStatus.CANCEL)
        );

        List<Item> items = itemRepository.findAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            int remainedStockQuantity = 10 * (i + 1);
            assertThat(item.getStockQuantity()).isEqualTo(remainedStockQuantity);
        }
    }

    @Test
    @DisplayName("재고가 충분하지 않은 상품 주문 처리")
    void notEnoughStockQuantityOrderTest() {
        Item item = Item.builder()
                .itemName("test not enough stock quantity item")
                .stockQuantity(0)
                .price(10000)
                .build();

        Long itemId = itemRepository.save(item).getId();

        MemberSessionDto sessionDto = new MemberSessionDto(member);

        List<OrderCreateRequestDto> requestDtoList = new ArrayList<>();

        OrderCreateRequestDto requestDto = new OrderCreateRequestDto();
        setField(requestDto, "itemId", itemId);
        setField(requestDto, "quantity", 10);

        requestDtoList.add(requestDto);

        assertThrows(NotEnoughStockQuantity.class,
                () ->orderService.order(requestDtoList, sessionDto));
    }

    @Test
    @DisplayName("이미 취소된 주문에 대한 주문 취소 테스트")
    void orderAlreadyCancelTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);

        Long orderId = createOrder(sessionDto);

        Long cancelOrderId = orderService.cancelOrder(orderId);

        assertThrows(OrderAlreadyCancelException.class,
                () -> orderService.cancelOrder(cancelOrderId));

        List<Item> items = itemRepository.findAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            int remainedStockQuantity = 10 * (i + 1);
            assertThat(item.getStockQuantity()).isEqualTo(remainedStockQuantity);
        }
    }

    @Test
    @DisplayName("주문 리스트 조회")
    void findOrdersTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);
        createOrder(sessionDto);

        Pageable pageable = PageRequest.of(0, 10);
        Page<OrderResponseDto> orders = orderService.findOrders(sessionDto, pageable);

        List<OrderResponseDto> orderList = orders.getContent();
        assertThat(orderList.size()).isEqualTo(1);

        OrderResponseDto order = orderList.get(0);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);

        List<OrderItemResponseDto> orderItems = order.getOrderItems();
        for (int i = 1; i <= orderItems.size() ; i++) {
            OrderItemResponseDto orderItem = orderItems.get(i - 1);

            assertThat(orderItem.getItemName()).isEqualTo("test item name " + i);
            assertThat(orderItem.getItemPrice()).isEqualTo(10000 * i);
            assertThat(orderItem.getQuantity()).isEqualTo(2 * i);
        }
    }

    @Test
    @DisplayName("주문 단건 조회")
    void findOrderTest() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);

        Long orderId = createOrder(sessionDto);

        OrderResponseDto order = orderService.findOrder(sessionDto, orderId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);

        List<OrderItemResponseDto> orderItems = order.getOrderItems();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemResponseDto orderItem = orderItems.get(i);

            int quantity = 2 * (i + 1);
            int itemPrice = 10000 * (i + 1);
            String itemName = "test item name " + (i + 1);

            assertAll(
                    () -> assertThat(orderItem.getItemName()).isEqualTo(itemName),
                    () -> assertThat(orderItem.getQuantity()).isEqualTo(quantity),
                    () -> assertThat(orderItem.getItemPrice()).isEqualTo(itemPrice)
            );
        }
    }

    private Long createOrder(MemberSessionDto sessionDto) {
        List<OrderCreateRequestDto> requestDtoList = new ArrayList<>();

        for (long i = 1; i <= 3; i++) {
            OrderCreateRequestDto requestDto = new OrderCreateRequestDto();
            setField(requestDto, "itemId", i);
            setField(requestDto, "quantity", (int) (2 * i));

            requestDtoList.add(requestDto);
        }

        return orderService.order(requestDtoList, sessionDto);
    }
}