package com.slcube.shop.business.item.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.item.dto.ItemListResponseDto;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import com.slcube.shop.business.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

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

        given(itemService.saveItem(any(ItemSaveRequestDto.class)))
                .willReturn(itemId);

        mockMvc.perform(post("/api/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(itemId.toString()))
                .andDo(print());

    }

    @Test
    @DisplayName("상품 단건 조회")
    void findItemTest() throws Exception {
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .itemId(1L)
                .itemName("test item")
                .price(10000)
                .build();

        given(itemService.findItem(anyLong()))
                .willReturn(itemResponseDto);

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(itemResponseDto)))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 다건 조회")
    void findAllItemsTest() throws Exception {
        List<ItemListResponseDto> result = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);

        for (int i = 1; i <= 3; i++) {
            ItemListResponseDto response = new ItemListResponseDto((long) i, "item" + i + " name", 10000 * i);
            result.add(response);
        }

        PageImpl<ItemListResponseDto> itemListResponseDtos = new PageImpl<>(result, pageable, result.size());

        Long categoryId = 1L;

        given(itemService.findItems(anyLong(), any(Pageable.class)))
                .willReturn(itemListResponseDtos);

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("categoryId", categoryId.toString());
        requestParam.set("page", "0");
        requestParam.set("size", "10");

        mockMvc.perform(get("/api/items?categoryId=1")
                        .with(csrf())
                        .params(requestParam))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(itemListResponseDtos)))
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

        given(itemService.updateItem(eq(itemId), any(ItemUpdateRequestDto.class)))
                .willReturn(itemId);

        mockMvc.perform(patch("/api/items/" + 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(itemId.toString()))
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