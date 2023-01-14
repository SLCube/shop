package com.slcube.shop.business.item.dto;

import com.slcube.shop.business.item.domain.Item;

public class ItemListResponseDto {

    private Long itemId;
    private String itemName;
    private int price;

    public ItemListResponseDto(Item item) {
        itemId = item.getId();
        itemName = item.getItemName();
        price = item.getPrice();
    }
}
