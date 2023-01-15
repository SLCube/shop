package com.slcube.shop.business.category.dto;

import com.slcube.shop.business.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategorySaveRequestDto {

    private String categoryName;

    @Builder
    private CategorySaveRequestDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category toEntity() {
        return Category.builder()
                .categoryName(this.categoryName)
                .build();
    }
}
