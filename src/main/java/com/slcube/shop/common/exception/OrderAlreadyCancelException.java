package com.slcube.shop.common.exception;

public class OrderAlreadyCancelException extends CustomException{
    public OrderAlreadyCancelException() {
        super(CustomErrorCode.ORDER_ALREADY_CANCEL);
    }
}
