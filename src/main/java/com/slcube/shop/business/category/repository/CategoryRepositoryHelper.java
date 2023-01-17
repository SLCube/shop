package com.slcube.shop.business.category.repository;

import com.slcube.shop.business.category.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryHelper {

    // helper class를 interface까지 정의할 필요가 있을까...?

    public Category findById(CategoryRepository categoryRepository, Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));
    }
}
