package com.slcube.shop.business.address.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressUpdateRequestDto {

    private Long addressId;
    private String city;
    private String zipcode;
    private String street;
    private String comment;
    private boolean isDefaultAddress;
}
