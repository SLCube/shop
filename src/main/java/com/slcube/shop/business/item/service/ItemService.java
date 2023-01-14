package com.slcube.shop.business.item.service;

import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemListResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    public Long saveItem(ItemSaveRequestDto requestDto);
    public Long updateItem(ItemUpdateRequestDto requestDto);
    public Long deleteItem(Long itemId);
    public ItemResponseDto findItem(Long itemId);
    public Page<ItemListResponseDto> findItems(Long categoryId, Pageable pageable);
}
