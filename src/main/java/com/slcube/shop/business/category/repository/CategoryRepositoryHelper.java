package com.slcube.shop.business.category.repository;

import com.slcube.shop.business.category.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryHelper {

    public Category findById(CategoryRepository categoryRepository, Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));
    }
}
