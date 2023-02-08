package com.slcube.shop.business.address.controller;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.address.service.AddressService;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public Long saveAddress(AddressSaveRequestDto requestDto, @AuthenticationPrincipal MemberContext memberContext) {
        return addressService.saveAddress(requestDto, memberContext.getMember());
    }

    @GetMapping
    public List<AddressListResponseDto> findAllAddress(@AuthenticationPrincipal MemberContext memberContext) {
        return addressService.findAllAddresses(memberContext.getMember());
    }

    @GetMapping("/{addressId}")
    public AddressResponseDto findAddress(@PathVariable Long addressId, @AuthenticationPrincipal MemberContext memberContext) {
        return addressService.findAddress(addressId, memberContext.getMember());
    }

    @PatchMapping("/{addressId}")
    public Long updateAddress(@PathVariable Long addressId, AddressUpdateRequestDto requestDto, @AuthenticationPrincipal MemberContext memberContext) {
        return addressService.updateAddress(addressId, requestDto, memberContext.getMember());
    }

    @DeleteMapping("/{addressId}")
    public Long deleteAddress(@PathVariable Long addressId, @AuthenticationPrincipal MemberContext memberContext) {
        return addressService.deleteAddress(addressId, memberContext.getMember());
    }
}
