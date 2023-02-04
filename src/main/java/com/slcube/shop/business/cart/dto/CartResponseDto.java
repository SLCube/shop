package com.slcube.shop.business.cart.dto;

import com.slcube.shop.business.cart.domain.Cart;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private Long cartId;
    private String itemName;
    private int price;
    private int quantity;

    public CartResponseDto(Cart cart) {
        cartId = cart.getId();
        itemName = cart.getItem().getItemName();
        price = cart.getItem().getPrice();
        quantity = cart.getQuantity();
    }
}
