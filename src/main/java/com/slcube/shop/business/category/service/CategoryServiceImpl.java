package com.slcube.shop.business.category.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.dto.*;
import com.slcube.shop.business.category.repository.CategoryRepository;
import com.slcube.shop.business.category.repository.CategoryRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryHelper categoryRepositoryHelper;

    @Override
    @Transactional
    public Long saveCategory(CategorySaveRequestDto requestDto) {
        Category category = CategoryMapper.toEntity(requestDto);
        return categoryRepository.save(category).getId();
    }

    @Override
    public Long updateCategory(CategoryUpdateRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();

        Category category = categoryRepositoryHelper.findById(categoryRepository, categoryId);
        category.updateCategory(requestDto.getCategoryName());
        return category.getId();
    }

    @Override
    public Long deleteCategory(Long categoryId) {
        Category category = categoryRepositoryHelper.findById(categoryRepository, categoryId);

        categoryRepository.delete(category);

        return category.getId();
    }

    @Override
    public CategoryResponseDto findCategory(Long categoryId) {
        Category category = categoryRepositoryHelper.findById(categoryRepository, categoryId);

        return new CategoryResponseDto(category);
    }

    @Override
    public List<CategoryListResponseDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
