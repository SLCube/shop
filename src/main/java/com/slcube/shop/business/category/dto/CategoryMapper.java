package com.slcube.shop.business.category.dto;

import com.slcube.shop.business.category.domain.Category;

public class CategoryMapper {

    public static Category toEntity(CategorySaveRequestDto requestDto) {
        return Category.builder()
                .categoryName(requestDto.getCategoryName())
                .build();
    }
}
