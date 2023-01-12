package com.slcube.shop.business.address;

import com.slcube.shop.business.address.AddressSaveRequestDto;
import com.slcube.shop.business.address.AddressUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public Long saveAddress(AddressSaveRequestDto requestDto);

    public Long updateAddress(AddressUpdateRequestDto requestDto);

    public Long deleteAddress(Long addressId);
}
