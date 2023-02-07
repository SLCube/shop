package com.slcube.shop.business.address.dto;

import com.slcube.shop.business.address.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private Long addressId;
    private String city;
    private String zipcode;
    private String street;
    private String comment;
    private boolean isDefaultAddress;

    public AddressResponseDto(Address address) {
        addressId = address.getId();
        city = address.getCity();
        zipcode = address.getZipcode();
        street = address.getStreet();
        comment = address.getComment();
        isDefaultAddress = address.isDefaultAddress();
    }
}
