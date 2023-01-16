package com.slcube.shop.business.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategorySaveRequestDto {

    private String categoryName;

    @Builder
    private CategorySaveRequestDto(String categoryName) {
        this.categoryName = categoryName;
    }
}
