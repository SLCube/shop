package com.slcube.shop.business.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListResponseDto {

    private Long cartItemId;
    private String itemName;
    private int price;
    private int quantity;
}
