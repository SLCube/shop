package com.slcube.shop.business.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.cart.service.CartService;
import com.slcube.shop.common.security.WithMockMember;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@WithMockMember
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MemberContext memberContext;

    @BeforeEach
    void setUp() {
        memberContext = (MemberContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Test
    @DisplayName("장바구니 저장")
    void saveCartTest() throws Exception {
        CartSaveRequestDto requestDto = new CartSaveRequestDto();
        requestDto.setItemId(1L);
        requestDto.setQuantity(10);

        given(cartService.saveCart(requestDto, memberContext.getMember()))
                .willReturn(1L);

        mockMvc.perform(post("/api/carts")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON)
                        .characterEncoding(UTF_8))
                .andExpect(status().isOk())
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

        given(cartService.findAllCarts(memberContext.getMember(), pageable))
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

        given(cartService.updateCart(cartId, requestDto))
                .willReturn(cartId);

        mockMvc.perform(patch("/api/carts/" + cartId)
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}