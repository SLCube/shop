package com.slcube.shop.business.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListResponseDto {

    private Long orderId;
    private String itemName;
    private int quantity;
}
