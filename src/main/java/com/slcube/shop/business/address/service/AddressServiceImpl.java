package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.address.dto.*;
import com.slcube.shop.business.address.repository.AddressRepository;
import com.slcube.shop.business.address.repository.AddressRepositoryHelper;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.exception.AddressNotRegisterAnymore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressRepositoryHelper addressRepositoryHelper;

    @Override
    @Transactional
    public Long saveAddress(AddressSaveRequestDto requestDto, Member member) {
        int countByMember = addressRepository.countByMember(member);
        if (countByMember >= 3) {
            throw new AddressNotRegisterAnymore();
        }
        Address address = AddressMapper.toEntity(requestDto, member);
        return addressRepository.save(address).getId();
    }

    @Override
    public AddressResponseDto findAddress(Long addressId, Member member) {
        Address address = addressRepositoryHelper.findByAddressIdAndMember(addressRepository, addressId, member);
        return new AddressResponseDto(address);
    }

    @Override
    public List<AddressListResponseDto> findAllAddresses(Member member) {
        return addressRepository.findByMember(member).stream()
                .map(AddressListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateAddress(Long addressId, AddressUpdateRequestDto requestDto, Member member) {
        Address address = addressRepositoryHelper.findByAddressIdAndMember(addressRepository, addressId, member);

        String city = requestDto.getCity();
        String zipcode = requestDto.getZipcode();
        String street = requestDto.getStreet();
        String comment = requestDto.getComment();
        boolean defaultAddress = requestDto.isDefaultAddress();
        address.updateAddress(city, zipcode, street, comment, defaultAddress);

        return address.getId();
    }

    @Override
    @Transactional
    public Long deleteAddress(Long addressId, Member member) {
        Address address = addressRepositoryHelper.findByAddressIdAndMember(addressRepository, addressId, member);
        addressRepository.delete(address);
        return address.getId();
    }
}
