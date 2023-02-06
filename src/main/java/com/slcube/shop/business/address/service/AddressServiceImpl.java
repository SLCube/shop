package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.address.dto.*;
import com.slcube.shop.business.address.repository.AddressRepository;
import com.slcube.shop.business.address.repository.AddressRepositoryHelper;
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
    public Long saveAddress(AddressSaveRequestDto requestDto, Long memberId) {
        if (addressRepository.countByMemberId(memberId) > 3) {
            throw new AddressNotRegisterAnymore();
        }
        Address address = AddressMapper.toEntity(requestDto);
        return addressRepository.save(address).getId();
    }

    @Override
    public AddressResponseDto findAddress(Long addressId, Long memberId) {
        Address address = addressRepositoryHelper.findByAddressIdAndMemberId(addressId, memberId);
        return new AddressResponseDto(address);
    }

    @Override
    public List<AddressListResponseDto> findAllAddresses(Long memberId) {
        return addressRepository.findByMemberId(memberId).stream()
                .map(AddressListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateAddress(AddressUpdateRequestDto requestDto, Long memberId) {

        Long addressId = requestDto.getAddressId();
        Address address = addressRepositoryHelper.findByAddressIdAndMemberId(addressId, memberId);

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
    public Long deleteAddress(Long addressId, Long memberId) {
        Address address = addressRepositoryHelper.findByAddressIdAndMemberId(addressId, memberId);
        addressRepository.delete(address);
        return address.getId();
    }
}
