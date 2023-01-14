package com.slcube.shop.business.item.dto;

import com.slcube.shop.business.item.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSaveRequestDto {

    private Long categoryId;
    private String itemName;
    private int price;
    private int stockQuantity;

    public Item toEntity() {

        return Item.builder()
                .itemName(this.itemName)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
