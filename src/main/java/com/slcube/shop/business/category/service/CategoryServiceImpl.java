package com.slcube.shop.business.category.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.dto.*;
import com.slcube.shop.business.category.repository.CategoryRepository;
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

    @Override
    @Transactional
    public Long saveCategory(CategorySaveRequestDto requestDto) {
        Category category = CategoryMapper.toEntity(requestDto);
        return categoryRepository.save(category).getId();
    }

    @Override
    public Long updateCategory(CategoryUpdateRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));
        category.updateCategory(requestDto.getCategoryName());
        return category.getId();
    }

    @Override
    public Long deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));

        categoryRepository.delete(category);

        return category.getId();
    }

    @Override
    public CategoryResponseDto findCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));

        return new CategoryResponseDto(category);
    }

    @Override
    public List<CategoryListResponseDto> findCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
