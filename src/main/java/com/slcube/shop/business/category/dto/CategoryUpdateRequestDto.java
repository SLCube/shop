package com.slcube.shop.business.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequestDto {

    private Long categoryId;
    private String categoryName;
}
