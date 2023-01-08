package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.category.CategoryListResponseDto;
import com.slcube.shop.business.dto.category.CategorySaveRequestDto;
import com.slcube.shop.business.dto.category.CategoryUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public Long saveCategory(CategorySaveRequestDto requestDto);
    public Long deleteCategory(Long id);
    public Long updateCategory(CategoryUpdateRequestDto requestDto);

    public List<CategoryListResponseDto> findCategories();
}
