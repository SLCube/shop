package com.slcube.shop.business.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateRequestDto {

    private Long itemId;
    private Long categoryId;
    private String itemName;
    private int price;
    private int stockQuantity;
}
