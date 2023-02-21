package com.slcube.shop.business.cart.dto;

import com.slcube.shop.business.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartListResponseDto {

    private Long cartId;
    private String itemName;
    private int price;
    private int quantity;

    public CartListResponseDto(Cart cart) {
        cartId = cart.getId();
        itemName = cart.getItem().getItemName();
        price = cart.getItem().getPrice();
        quantity = cart.getQuantity();
    }
}
