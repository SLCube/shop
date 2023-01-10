package com.slcube.shop.business.dto.item;

import com.slcube.shop.business.domain.Item;
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
