package com.slcube.shop.common.exception;

public class ItemNotFoundException extends CustomException{

    public ItemNotFoundException() {
        super(CustomErrorCode.ITEMS_NOT_FOUND);
    }
}
