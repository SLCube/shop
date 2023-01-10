package com.slcube.shop.business.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressSaveRequestDto {

    private String city;
    private String zipcode;
    private String street;
    private String comment;
    private boolean isDefaultAddress;
}
