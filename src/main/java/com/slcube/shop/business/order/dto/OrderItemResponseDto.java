package com.slcube.shop.business.order.dto;

import lombok.Getter;

@Getter
public class OrderItemResponseDto {

    private Long orderItemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
}
