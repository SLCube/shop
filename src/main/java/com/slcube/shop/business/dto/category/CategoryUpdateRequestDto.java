package com.slcube.shop.business.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequestDto {

    private Long categoryId;
    private String categoryName;
}
