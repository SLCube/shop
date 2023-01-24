package com.slcube.shop.business.category.repository;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.common.exception.CategoryNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryHelper {

    public Category findById(CategoryRepository categoryRepository, Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException());
    }
}
