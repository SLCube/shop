package com.slcube.shop.business.category.dto;

import com.slcube.shop.business.category.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {

    private Long categoryId;
    private String categoryName;

    public CategoryResponseDto(Category category) {
        categoryId = category.getId();
        categoryName = category.getCategoryName();
    }
}
