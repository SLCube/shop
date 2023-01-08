package com.slcube.shop.business.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private int price;
}
