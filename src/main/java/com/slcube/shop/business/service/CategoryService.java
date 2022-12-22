package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.category.CategorySaveRequestDto;
import com.slcube.shop.business.dto.category.CategoryUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    public Long registerCategory(CategorySaveRequestDto requestDto);
    public Long deleteCategory(Long id);
    public Long updateCategory(CategoryUpdateRequestDto requestDto);
}
