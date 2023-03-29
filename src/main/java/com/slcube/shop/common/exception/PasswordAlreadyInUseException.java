package com.slcube.shop.common.exception;

public class PasswordAlreadyInUseException extends CustomException {
    public PasswordAlreadyInUseException() {
        super(CustomErrorCode.PASSWORD_ALREADY_IN_USE);
    }
}
