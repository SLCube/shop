package com.slcube.shop.business.address.repository;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.exception.AddressNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryHelper {

    public Address findByAddressIdAndMember(AddressRepository addressRepository, Long addressId, Member member) {
        return addressRepository.findByMemberAndAddressId(addressId, member)
                .orElseThrow(AddressNotFoundException::new);
    }
}
