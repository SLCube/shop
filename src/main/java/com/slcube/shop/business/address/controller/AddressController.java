package com.slcube.shop.business.address.controller;

import com.slcube.shop.business.address.dto.AddressListResponseDto;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.address.service.AddressService;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public Long saveAddress(@RequestBody @Valid AddressSaveRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        return addressService.saveAddress(requestDto, sessionDto);
    }

    @GetMapping
    public List<AddressListResponseDto> findAllAddress(@AuthenticationPrincipal MemberSessionDto sessionDto) {
        return addressService.findAllAddresses(sessionDto);
    }

    @GetMapping("/{addressId}")
    public AddressResponseDto findAddress(@PathVariable Long addressId, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        return addressService.findAddress(addressId, sessionDto);
    }

    @PatchMapping("/{addressId}")
    public Long updateAddress(@PathVariable Long addressId, @RequestBody @Valid AddressUpdateRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        return addressService.updateAddress(addressId, requestDto, sessionDto);
    }

    @DeleteMapping("/{addressId}")
    public Long deleteAddress(@PathVariable Long addressId, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        return addressService.deleteAddress(addressId, sessionDto);
    }
}
