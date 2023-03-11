package com.slcube.shop.common.exception;

public class NotEnoughStockQuantity extends CustomException {
    public NotEnoughStockQuantity() {
        super(CustomErrorCode.NOT_ENOUGH_STOCK_QUANTITY);
    }
}
