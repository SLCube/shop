package com.slcube.shop.business.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.domain.OrderStatus;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderItemResponseDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import com.slcube.shop.business.order.service.OrderService;
import com.slcube.shop.common.exception.OrderAlreadyCancelException;
import com.slcube.shop.common.security.TestSecurityConfig;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@WithMockMember
@Import(TestSecurityConfig.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    @DisplayName("주문 테스트")
    void orderTest() throws Exception {

        List<OrderCreateRequestDto> requestDtoList = new ArrayList<>();
        OrderCreateRequestDto requestDto = new OrderCreateRequestDto();
        setField(requestDto, "itemId", 1L);
        setField(requestDto, "quantity", 10);

        requestDtoList.add(requestDto);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoList)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    void cancelOrderTest() throws Exception {
        Long orderId = 1L;

        given(orderService.cancelOrder(anyLong()))
                .willReturn(orderId);

        mockMvc.perform(patch("/api/orders/" + orderId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("이미 취소된 주문에 대한 주문 취소 테스트")
    void orderAlreadyCancelTest() throws Exception {
        Long orderId = 1L;
        given(orderService.cancelOrder(orderId))
                .willThrow(new OrderAlreadyCancelException());

        mockMvc.perform(patch("/api/orders/" + orderId))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("주문 리스트 조회")
    void findOrdersTest() throws Exception {
        List<OrderResponseDto> orders = new ArrayList<>();
        orders.add(createOrder());
        Pageable pageable = PageRequest.of(0, 10);

        PageImpl<OrderResponseDto> result = new PageImpl<>(orders, pageable, orders.size());
        given(orderService.findOrders(any(MemberSessionDto.class), any(Pageable.class)))
                .willReturn(result);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)))
                .andDo(print());
    }

    @Test
    @DisplayName("주문 단건 조회")
    void findOrderTest() throws Exception {
        Long orderId = 1L;
        OrderResponseDto order = createOrder();

        given(orderService.findOrder(any(MemberSessionDto.class), anyLong()))
                .willReturn(order);

        mockMvc.perform(get("/api/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(order)))
                .andDo(print());

    }

    private OrderResponseDto createOrder() {
        OrderResponseDto order = new OrderResponseDto();
        setField(order, "orderId", 1L);
        setField(order, "orderStatus", OrderStatus.ORDER);
        setField(order, "orderDate", LocalDateTime.now());

        List<OrderItemResponseDto> orderItems = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            OrderItemResponseDto orderItem = new OrderItemResponseDto();
            setField(orderItem, "orderItemId", (long) i);
            setField(orderItem, "itemName", "test item name " + i);
            setField(orderItem, "itemPrice", 10000 * i);
            setField(orderItem, "quantity", 2 * i);
            orderItems.add(orderItem);
        }

        setField(order, "orderItems", orderItems);

        return order;
    }
}