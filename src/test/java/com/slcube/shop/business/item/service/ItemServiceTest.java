package com.slcube.shop.business.item.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.repository.CategoryRepository;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    private Long categoryId1;
    private Long categoryId2;

    @BeforeEach
    void beforeEach() {
        Category category1 = Category.builder()
                .categoryName("test CategoryName1")
                .build();

        Category category2 = Category.builder()
                .categoryName("test CategoryName2")
                .build();

        categoryId1 = categoryRepository.save(category1).getId();
        categoryId2 = categoryRepository.save(category2).getId();
    }

    @Test
    @DisplayName("상품 등록")
    void saveItem() {
        // given
        ItemSaveRequestDto requestDto = new ItemSaveRequestDto();
        requestDto.setCategoryId(categoryId1);
        requestDto.setItemName("test item name");
        requestDto.setStockQuantity(10);
        requestDto.setPrice(10000);

        // when
        Long itemId = itemService.saveItem(requestDto);

        // then
        ItemResponseDto findItem = itemService.findItem(itemId);
        assertAll(
                () -> assertThat(findItem.getItemName()).isEqualTo("test item name"),
                () -> assertThat(findItem.getPrice()).isEqualTo(10000)
        );
    }

    @Test
    @DisplayName("상품 수정")
    void updateItem() {
        // given
        ItemSaveRequestDto requestDto = new ItemSaveRequestDto();
        requestDto.setCategoryId(categoryId1);
        requestDto.setItemName("test item name");
        requestDto.setStockQuantity(10);
        requestDto.setPrice(10000);

        Long itemId = itemService.saveItem(requestDto);


        ItemUpdateRequestDto updateRequestDto = new ItemUpdateRequestDto();
        updateRequestDto.setItemId(itemId);
        updateRequestDto.setPrice(20000);
        updateRequestDto.setCategoryId(categoryId2);
        updateRequestDto.setItemName("test item name 2");

        // when
        Long updateItemId = itemService.updateItem(updateRequestDto);

        // then
        // 카테고리가 바뀐게 체크가 안되네..?
        ItemResponseDto item = itemService.findItem(updateItemId);
        assertAll(
                () -> assertThat(item.getItemName()).isEqualTo("test item name 2"),
                () -> assertThat(item.getPrice()).isEqualTo(20000)
        );
    }

    @Test
    @DisplayName("상품 삭제")
    void deleteItem() {
        // given
        ItemSaveRequestDto requestDto = new ItemSaveRequestDto();
        requestDto.setCategoryId(categoryId1);
        requestDto.setItemName("test item name");
        requestDto.setStockQuantity(10);
        requestDto.setPrice(10000);

        Long itemId = itemService.saveItem(requestDto);

        // when
        Long deleteItemId = itemService.deleteItem(itemId);

        // then
        assertThrows(IllegalArgumentException.class,
                () -> itemService.findItem(deleteItemId));
    }
}