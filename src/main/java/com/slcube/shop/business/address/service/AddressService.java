package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public Long saveAddress(AddressSaveRequestDto requestDto);

    public Long updateAddress(AddressUpdateRequestDto requestDto);

    public Long deleteAddress(Long addressId);
}
