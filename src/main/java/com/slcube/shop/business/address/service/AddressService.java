package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.member.dto.MemberSessionDto;

import java.util.List;

public interface AddressService {

    Long saveAddress(AddressSaveRequestDto requestDto, MemberSessionDto sessionDto);
    AddressResponseDto findAddress(Long addressId, MemberSessionDto sessionDto);
    List<AddressListResponseDto> findAllAddresses(MemberSessionDto sessionDto);
    Long updateAddress(Long addressId, AddressUpdateRequestDto requestDto, MemberSessionDto sessionDto);
    Long deleteAddress(Long addressId, MemberSessionDto sessionDto);
}
