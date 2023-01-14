package com.slcube.shop.business.category.dto;

import com.slcube.shop.business.category.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySaveRequestDto {

    private String categoryName;

    public Category toEntity() {
        return Category.builder()
                .categoryName(this.categoryName)
                .build();
    }
}
