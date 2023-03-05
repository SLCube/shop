package com.slcube.shop.business.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
}