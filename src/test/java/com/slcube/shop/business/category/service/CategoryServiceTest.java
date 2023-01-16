package com.slcube.shop.business.category.service;

import com.slcube.shop.business.category.dto.CategoryResponseDto;
import com.slcube.shop.business.category.dto.CategorySaveRequestDto;
import com.slcube.shop.business.category.dto.CategoryUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 저장")
    void saveCategoryTest() {
        // given
        CategorySaveRequestDto requestDto = CategorySaveRequestDto.builder()
                .categoryName("test CategoryName")
                .build();

        // when
        Long categoryId = categoryService.saveCategory(requestDto);

        // then
        CategoryResponseDto responseDto = categoryService.findCategory(categoryId);

        assertThat(responseDto.getCategoryName()).isEqualTo("test CategoryName");
    }

    @Test
    @DisplayName("카테고리 수정")
    void updateCategoryTest() {
        // given
        CategorySaveRequestDto requestDto = CategorySaveRequestDto.builder()
                .categoryName("test CategoryName")
                .build();

        Long categoryId = categoryService.saveCategory(requestDto);

        CategoryUpdateRequestDto updateRequestDto = new CategoryUpdateRequestDto();
        updateRequestDto.setCategoryId(categoryId);
        updateRequestDto.setCategoryName("update CategoryName");

        // when
        categoryService.updateCategory(updateRequestDto);

        // then
        CategoryResponseDto responseDto = categoryService.findCategory(categoryId);
        assertThat(responseDto.getCategoryName()).isEqualTo("update CategoryName");
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteCategoryTest() {
        // given
        CategorySaveRequestDto requestDto = CategorySaveRequestDto.builder()
                .categoryName("test CategoryName")
                .build();

        Long categoryId = categoryService.saveCategory(requestDto);

        // when
        Long deletedCategoryId = categoryService.deleteCategory(categoryId);

        // then
        assertThrows(IllegalArgumentException.class,
                () -> categoryService.findCategory(deletedCategoryId));
    }
}