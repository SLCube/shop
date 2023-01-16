package com.slcube.shop.business.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSaveRequestDto {

    private Long categoryId;
    private String itemName;
    private int price;
    private int stockQuantity;
}
