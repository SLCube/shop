package com.slcube.shop.business.item.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.repository.CategoryRepository;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.domain.ItemCategory;
import com.slcube.shop.business.item.dto.ItemListResponseDto;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import com.slcube.shop.business.item.repository.ItemCategoryRepository;
import com.slcube.shop.business.item.repository.ItemRepository;
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
    private final ItemCategoryRepository itemCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long saveItem(ItemSaveRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다. id : " + categoryId));

        Item item = requestDto.toEntity();
        ItemCategory itemCategory = ItemCategory.createItemCategory(category, item);
        item.addCategory(itemCategory);

        return itemRepository.save(item).getId();
    }

    @Override
    @Transactional
    public Long updateItem(ItemUpdateRequestDto requestDto) {
        // 뭔가 많이 이상한데...
        Long itemId = requestDto.getItemId();
        Long categoryId = requestDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));
        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템 정보를 찾을 수 없습니다. id = " + itemId));
        ItemCategory itemCategory = itemCategoryRepository.findByCategoryIdAndItemId(categoryId, itemId);

        itemCategory.updateCategory(category);
        item.updateItem(requestDto);
        return requestDto.getItemId();
    }

    @Override
    @Transactional
    public Long deleteItem(Long itemId) {
        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템 정보를 찾을 수 없습니다. id = " + itemId));
        item.deleteItem();
        return item.getId();
    }

    @Override
    public ItemResponseDto findItem(Long itemId) {
        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템 정보를 찾을 수 없습니다. id = " + itemId));

        return ItemResponseDto.toDto(item);
    }

    @Override
    public Page<ItemListResponseDto> findItems(Long categoryId, Pageable pageable) {
        List<ItemListResponseDto> responseDtoList = itemRepository.findByCategory(categoryId)
                .stream().map(ItemListResponseDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(responseDtoList, pageable, responseDtoList.size());
    }
}
