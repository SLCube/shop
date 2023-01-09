package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.item.ItemResponseDto;
import com.slcube.shop.business.dto.item.ItemListResponseDto;
import com.slcube.shop.business.dto.item.ItemSaveRequestDto;
import com.slcube.shop.business.dto.item.ItemUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    public Long saveItem(ItemSaveRequestDto requestDto);
    public Long updateItem(ItemUpdateRequestDto requestDto);
    public Long deleteItem(Long itemId);
    public ItemResponseDto findItem(Long itemId);
    public Page<ItemListResponseDto> findItems(int pageNo, Long categoryId);
}
