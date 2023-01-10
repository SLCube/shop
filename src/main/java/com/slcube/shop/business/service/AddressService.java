package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.address.AddressSaveRequestDto;
import com.slcube.shop.business.dto.address.AddressUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public Long saveAddress(AddressSaveRequestDto requestDto);

    public Long updateAddress(AddressUpdateRequestDto requestDto);

    public Long deleteAddress(Long addressId);
}
