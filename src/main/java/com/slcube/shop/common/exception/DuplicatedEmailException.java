package com.slcube.shop.common.exception;

public class DuplicatedEmailException extends CustomException {
    public DuplicatedEmailException() {
        super(CustomErrorCode.DUPLICATED_EMAIL);
    }
}
