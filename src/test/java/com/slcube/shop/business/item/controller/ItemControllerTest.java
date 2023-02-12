package com.slcube.shop.business.item.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import com.slcube.shop.business.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
@WithMockUser
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("상품 저장")
    void saveItemTest() throws Exception {
        Long itemId = 1L;
        ItemSaveRequestDto requestDto = new ItemSaveRequestDto();
        requestDto.setItemName("test item");
        requestDto.setPrice(10000);
        requestDto.setCategoryId(1L);
        requestDto.setStockQuantity(10);

        given(itemService.saveItem(requestDto))
                .willReturn(itemId);

        mockMvc.perform(post("/api/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("상품 단건 조회")
    void findItemTest() throws Exception {
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .itemId(1L)
                .itemName("test item")
                .price(10000)
                .build();

        given(itemService.findItem(1L))
                .willReturn(itemResponseDto);

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(itemResponseDto)))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정")
    void updateItemTest() throws Exception {
        ItemUpdateRequestDto requestDto = new ItemUpdateRequestDto();

        requestDto.setCategoryId(1L);
        requestDto.setPrice(15000);
        requestDto.setItemName("item update name");
        requestDto.setStockQuantity(20);

        Long itemId = 1L;

        given(itemService.updateItem(itemId, requestDto))
                .willReturn(itemId);

        mockMvc.perform(patch("/api/items/" + 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("상품 삭제")
    void deleteItemTest() throws Exception {
        Long itemId = 1L;

        given(itemService.deleteItem(itemId))
                .willReturn(itemId);

        mockMvc.perform(delete("/api/items/" + itemId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(itemId.toString()))
                .andDo(print());
    }
}