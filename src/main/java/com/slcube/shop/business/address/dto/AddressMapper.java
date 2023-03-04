package com.slcube.shop.business.address.dto;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.member.dto.MemberSessionDto;

public class AddressMapper {

    public static Address toEntity(AddressSaveRequestDto requestDto, MemberSessionDto sessionDto) {
        return Address.builder()
                .city(requestDto.getCity())
                .zipcode(requestDto.getZipcode())
                .street(requestDto.getStreet())
                .comment(requestDto.getComment())
                .isDefaultAddress(requestDto.getIsDefaultAddress())
                .memberId(sessionDto.getMemberId())
                .build();
    }
}
