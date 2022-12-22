package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.item.ItemListResponseDto;
import com.slcube.shop.business.dto.item.ItemSaveRequestDto;
import com.slcube.shop.business.dto.item.ItemUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    public Long registerItem(ItemSaveRequestDto requestDto);
    public Long deleteItem(Long id);
    public Long updateItem(ItemUpdateRequestDto requestDto);
    public Page<ItemListResponseDto> getItems(int pageNo, Long categoryId);
}
