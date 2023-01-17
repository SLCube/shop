package com.slcube.shop.business.item.dto;

import com.slcube.shop.business.item.domain.Item;

public class ItemMapper {

    public static Item toEntity(ItemSaveRequestDto requestDto) {
        return Item.builder()
                .itemName(requestDto.getItemName())
                .price(requestDto.getPrice())
                .stockQuantity(requestDto.getStockQuantity())
                .build();
    }
}
