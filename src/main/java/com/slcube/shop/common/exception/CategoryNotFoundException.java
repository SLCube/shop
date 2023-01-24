package com.slcube.shop.common.exception;

public class CategoryNotFoundException extends CustomException{
    public CategoryNotFoundException() {
        super(CustomErrorCode.CATEGORIES_NOT_FOUND);
    }
}
