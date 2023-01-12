package com.slcube.shop.business.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartSaveRequestDto {

    private Long itemId;
    private int quantity;
}
