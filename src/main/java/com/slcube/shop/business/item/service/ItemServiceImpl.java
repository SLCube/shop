package com.slcube.shop.business.item.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.repository.CategoryRepository;
import com.slcube.shop.business.category.repository.CategoryRepositoryHelper;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.dto.*;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.item.repository.ItemRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositoryHelper itemRepositoryHelper;
    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryHelper categoryRepositoryHelper;

    @Override
    @Transactional
    public Long saveItem(ItemSaveRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();

        Item item = ItemMapper.toEntity(requestDto);
        Category category = categoryRepositoryHelper.findById(categoryRepository, categoryId);

        item.addCategory(category);

        return itemRepository.save(item).getId();
    }

    @Override
    @Transactional
    public Long updateItem(ItemUpdateRequestDto requestDto) {

        Long itemId = requestDto.getItemId();
        Long categoryId = requestDto.getCategoryId();

        Item item = itemRepositoryHelper.findByNotDeleted(itemRepository, itemId);
        Category category = categoryRepositoryHelper.findById(categoryRepository, categoryId);

        item.updateItem(requestDto, category);
        return requestDto.getItemId();
    }

    @Override
    @Transactional
    public Long deleteItem(Long itemId) {
        Item item = itemRepositoryHelper.findByNotDeleted(itemRepository, itemId);

        item.deleteItem();

        return itemId;
    }

    @Override
    public ItemResponseDto findItem(Long itemId) {
        Item item = itemRepositoryHelper.findByNotDeleted(itemRepository, itemId);

        return new ItemResponseDto(item);
    }

    @Override
    public Page<ItemListResponseDto> findItems(Long categoryId, Pageable pageable) {
        List<ItemListResponseDto> result = itemRepository.findByCategoryId(categoryId, pageable).stream()
                .map(ItemListResponseDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}
