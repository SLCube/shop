package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.member.domain.Member;

import java.util.List;

public interface AddressService {

    Long saveAddress(AddressSaveRequestDto requestDto, Member member);
    AddressResponseDto findAddress(Long addressId, Member member);
    List<AddressListResponseDto> findAllAddresses(Member member);
    Long updateAddress(Long addressId, AddressUpdateRequestDto requestDto, Member member);
    Long deleteAddress(Long addressId, Member member);
}
