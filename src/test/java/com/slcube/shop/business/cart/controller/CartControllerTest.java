package com.slcube.shop.business.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.service.CartService;
import com.slcube.shop.common.security.WithMockMember;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.charset.StandardCharsets.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(status().isOk());
    }
}