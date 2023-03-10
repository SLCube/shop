package com.slcube.shop.common.exception;

public class OrderNotFoundException extends CustomException{
    public OrderNotFoundException() {
        super(CustomErrorCode.ORDER_NOT_FOUND);
    }
}
