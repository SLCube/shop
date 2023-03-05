package com.slcube.shop.business.order.dto;

import lombok.Getter;

@Getter
public class OrderCreateRequestDto {

    private Long itemId;
    private int quantity;
}
