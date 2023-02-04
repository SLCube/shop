package com.slcube.shop.common.exception;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

public class CartItemNotFoundException extends CustomException{
    public CartItemNotFoundException() {
        super(CART_ITEMS_NOT_FOUND);
    }
}
