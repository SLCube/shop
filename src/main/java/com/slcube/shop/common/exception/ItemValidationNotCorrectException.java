package com.slcube.shop.common.exception;

public class ItemValidationNotCorrectException extends CustomException {

    public ItemValidationNotCorrectException() {
        super(CustomErrorCode.ITEM_VALIDATION_NOT_CORRECT);
    }
}
