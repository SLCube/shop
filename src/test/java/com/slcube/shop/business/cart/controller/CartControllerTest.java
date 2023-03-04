package com.slcube.shop.business.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.cart.service.CartService;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@WithMockMember
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("장바구니 저장")
    void saveCartTest() throws Exception {
        Long cartId = 1L;

        CartSaveRequestDto requestDto = new CartSaveRequestDto();
        requestDto.setItemId(1L);
        requestDto.setQuantity(10);

        given(cartService.saveCart(any(CartSaveRequestDto.class), any(MemberSessionDto.class)))
                .willReturn(cartId);

        mockMvc.perform(post("/api/carts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(cartId.toString()))
                .andExpect(content().json(cartId.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니에 저장한 상품리스트 조회")
    void findAllCartsTest() throws Exception {
        List<CartListResponseDto> carts = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);

        for (int i = 1; i <= 3; i++) {
            CartListResponseDto responseDto = new CartListResponseDto((long) i, "test item name " + i, 2000 * i, i);
            carts.add(responseDto);
        }

        PageImpl<CartListResponseDto> result = new PageImpl<>(carts, pageable, carts.size());

        given(cartService.findAllCarts(any(MemberSessionDto.class), any(Pageable.class)))
                .willReturn(result);

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("page", "0");
        requestParam.set("size", "10");

        mockMvc.perform(get("/api/carts")
                        .with(csrf())
                        .queryParams(requestParam))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(result)))
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니 상품 수량 수정")
    void updateCartTest() throws Exception {
        Long cartId = 1L;

        CartUpdateRequestDto requestDto = new CartUpdateRequestDto();
        requestDto.setQuantity(15);

        given(cartService.updateCart(anyLong(), any(CartUpdateRequestDto.class)))
                .willReturn(cartId);

        mockMvc.perform(patch("/api/carts/" + cartId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(cartId.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니 상품 삭제")
    void deleteCartTest() throws Exception {
        Long cartId = 1L;

        given(cartService.deleteCart(cartId))
                .willReturn(cartId);

        mockMvc.perform(delete("/api/carts/" + cartId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(cartId.toString()))
                .andDo(print());
    }
}