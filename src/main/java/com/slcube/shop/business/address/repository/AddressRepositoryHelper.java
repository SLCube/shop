package com.slcube.shop.business.address.repository;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.common.exception.AddressNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryHelper {

    public Address findByAddressIdAndMemberId(AddressRepository addressRepository, Long addressId, Long memberId) {
        return addressRepository.findByAddressIdAndMemberId(addressId, memberId)
                .orElseThrow(AddressNotFoundException::new);
    }
}
