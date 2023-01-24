package com.slcube.shop.common.exception;

public class ReviewNotFoundException extends CustomException{
    public ReviewNotFoundException() {
        super(CustomErrorCode.REVIEWS_NOT_FOUND);
    }
}
