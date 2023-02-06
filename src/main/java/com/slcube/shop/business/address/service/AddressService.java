package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public Long saveAddress(AddressSaveRequestDto requestDto, Long memberId);
    public AddressResponseDto findAddress(Long addressId, Long memberId);
    public List<AddressListResponseDto> findAllAddresses(Long memberId);
    public Long updateAddress(AddressUpdateRequestDto requestDto, Long memberId);
    public Long deleteAddress(Long addressId, Long memberId);
}
