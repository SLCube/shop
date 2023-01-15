package com.slcube.shop.business.item.service;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.category.repository.CategoryRepository;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.dto.ItemListResponseDto;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
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
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long saveItem(ItemSaveRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();

        Item item = requestDto.toEntity();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));

        item.addCategory(category);

        return itemRepository.save(item).getId();
    }

    @Override
    @Transactional
    public Long updateItem(ItemUpdateRequestDto requestDto) {

        Long itemId = requestDto.getItemId();
        Long categoryId = requestDto.getCategoryId();

        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다. id = " + itemId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 정보를 찾을 수 없습니다. id = " + categoryId));

        item.updateItem(requestDto, category);
        return requestDto.getItemId();
    }

    @Override
    @Transactional
    public Long deleteItem(Long itemId) {
        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다. id = " + itemId));

        item.deleteItem();

        return itemId;
    }

    @Override
    public ItemResponseDto findItem(Long itemId) {
        Item item = itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다. id = " + itemId));

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
