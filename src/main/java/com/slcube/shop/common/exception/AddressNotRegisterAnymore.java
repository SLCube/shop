package com.slcube.shop.common.exception;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

public class AddressNotRegisterAnymore extends CustomException{

    public AddressNotRegisterAnymore() {
        super(ADDRESS_NOT_REGISTER_ANYMORE);
    }
}
