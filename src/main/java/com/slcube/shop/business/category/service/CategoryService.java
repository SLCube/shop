package com.slcube.shop.business.category.service;

import com.slcube.shop.business.category.dto.CategoryListResponseDto;
import com.slcube.shop.business.category.dto.CategorySaveRequestDto;
import com.slcube.shop.business.category.dto.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {

    public Long saveCategory(CategorySaveRequestDto requestDto);
    public Long deleteCategory(Long categoryId);
    public Long updateCategory(CategoryUpdateRequestDto requestDto);

    public List<CategoryListResponseDto> findCategories();
}
