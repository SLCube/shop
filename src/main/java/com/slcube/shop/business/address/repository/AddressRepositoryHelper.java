package com.slcube.shop.business.address.repository;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.common.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressRepositoryHelper {

    private final AddressRepository addressRepository;

    public Address findByAddressIdAndMemberId(Long addressId, Long memberId) {
        return addressRepository.findByMemberIdAndAddressId(addressId, memberId)
                .orElseThrow(() -> new AddressNotFoundException());
    }
}
