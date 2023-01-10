package com.slcube.shop.business.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequestDto {

    private Long itemId;
    private int quantity;
}
