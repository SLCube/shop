package com.slcube.shop.business.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListResponseDto {

    private Long orderId;
    private String itemName;
    private int quantity;
}
