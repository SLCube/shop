package com.slcube.shop.common.exception;

public class MemberNotFoundException extends CustomException{
    public MemberNotFoundException() {
        super(CustomErrorCode.MEMBER_NOT_FOUND);
    }
}
