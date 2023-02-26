package com.slcube.shop.business.category.service;

import com.slcube.shop.business.category.dto.CategoryListResponseDto;
import com.slcube.shop.business.category.dto.CategoryResponseDto;
import com.slcube.shop.business.category.dto.CategorySaveRequestDto;
import com.slcube.shop.business.category.dto.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {

    Long saveCategory(CategorySaveRequestDto requestDto);
    Long updateCategory(CategoryUpdateRequestDto requestDto);
    Long deleteCategory(Long categoryId);
    CategoryResponseDto findCategory(Long categoryId);
    List<CategoryListResponseDto> findAllCategories();
}
