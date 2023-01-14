package com.slcube.shop.business.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequestDto {

    private Long itemId;
    private int quantity;
}
