package com.slcube.shop.business.category.dto;

import com.slcube.shop.business.category.domain.Category;
import lombok.Getter;

@Getter
public class CategoryListResponseDto {

    private Long categoryId;
    private String categoryName;

    public CategoryListResponseDto(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.getCategoryName();
    }
}
