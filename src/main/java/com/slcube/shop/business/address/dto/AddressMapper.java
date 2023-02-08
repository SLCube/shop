package com.slcube.shop.business.address.dto;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.member.domain.Member;

public class AddressMapper {

    public static Address toEntity(AddressSaveRequestDto requestDto, Member member) {
        return Address.builder()
                .city(requestDto.getCity())
                .zipcode(requestDto.getZipcode())
                .street(requestDto.getStreet())
                .comment(requestDto.getComment())
                .isDefaultAddress(requestDto.isDefaultAddress())
                .member(member)
                .build();
    }
}