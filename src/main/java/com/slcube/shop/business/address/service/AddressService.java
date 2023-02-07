package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public Long saveAddress(AddressSaveRequestDto requestDto, Member member);
    public AddressResponseDto findAddress(Long addressId, Member member);
    public List<AddressListResponseDto> findAllAddresses(Member member);
    public Long updateAddress(Long addressId, AddressUpdateRequestDto requestDto, Member member);
    public Long deleteAddress(Long addressId, Member member);
}
