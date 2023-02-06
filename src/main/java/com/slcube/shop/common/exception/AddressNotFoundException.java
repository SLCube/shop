package com.slcube.shop.common.exception;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

public class AddressNotFoundException extends CustomException {
    public AddressNotFoundException() {
        super(ADDRESS_NOT_FOUND);
    }
}
