package com.slcube.shop.business.address.dto;

import com.slcube.shop.business.address.domain.Address;

public class AddressMapper {

    public static Address toEntity(AddressSaveRequestDto requestDto) {
        return Address.builder()
                .city(requestDto.getCity())
                .zipcode(requestDto.getZipcode())
                .street(requestDto.getStreet())
                .comment(requestDto.getComment())
                .isDefaultAddress(requestDto.isDefaultAddress())
                .build();
    }
}
