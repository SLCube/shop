package com.slcube.shop.business.item.dto;

import com.slcube.shop.business.item.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private int price;

    @Builder
    private ItemResponseDto(Long itemId, String itemName, int price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public ItemResponseDto(Item item) {
        itemId = item.getId();
        itemName = item.getItemName();
        price = item.getPrice();
    }
}
