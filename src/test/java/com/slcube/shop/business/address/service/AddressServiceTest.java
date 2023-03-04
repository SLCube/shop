package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.common.exception.AddressNotFoundException;
import com.slcube.shop.common.exception.AddressNotRegisterAnymore;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@WithMockMember
class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private MemberRepository memberRepository;
    private Member member;

    @BeforeEach
    void beforeEach() {
        MemberSessionDto sessionDto = (MemberSessionDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        member = Member.builder()
                .email(sessionDto.getLoginEmail())
                .username(sessionDto.getUsername())
                .password("test password")
                .build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("배송지 저장")
    void saveAddressTest() {
        AddressSaveRequestDto requestDto = createAddress();
        MemberSessionDto sessionDto = createSessionDto();
        Long saveId = addressService.saveAddress(requestDto, sessionDto);
        AddressResponseDto findAddress = addressService.findAddress(saveId, sessionDto);

        assertAll(
                () -> assertThat(findAddress.getCity()).isEqualTo("test city"),
                () -> assertThat(findAddress.getZipcode()).isEqualTo("test zipcode"),
                () -> assertThat(findAddress.getStreet()).isEqualTo("test street"),
                () -> assertThat(findAddress.getComment()).isEqualTo("test comment"),
                () -> assertThat(findAddress.getIsDefaultAddress()).isEqualTo(true)
        );
    }

    @Test
    @DisplayName("배송지가 3개일때 배송지 추가 시도")
    void addressNotRegisterAnymoreTest() {

        MemberSessionDto sessionDto = createSessionDto();

        for (int i = 0; i < 3; i++) {
            AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
            requestDto.setIsDefaultAddress(false);
            requestDto.setCity("test city " + i);
            requestDto.setZipcode("test zipcode " + i);
            requestDto.setStreet("test street " + i);
            requestDto.setComment("test comment " + i);
            addressService.saveAddress(requestDto, sessionDto);
        }

        AddressSaveRequestDto fourthAddress = new AddressSaveRequestDto();
        fourthAddress.setCity("fourth address city");
        fourthAddress.setZipcode("fourth address zipcode");
        fourthAddress.setStreet("fourth address street");

        assertThrows(AddressNotRegisterAnymore.class,
                () -> addressService.saveAddress(fourthAddress, sessionDto));
    }

    @Test
    @DisplayName("배송지 수정")
    void updateAddressTest() {
        MemberSessionDto sessionDto = createSessionDto();

        AddressSaveRequestDto addressSaveRequestDto = createAddress();

        Long addressId = addressService.saveAddress(addressSaveRequestDto, sessionDto);

        AddressUpdateRequestDto addressUpdateRequestDto = new AddressUpdateRequestDto();

        addressUpdateRequestDto.setCity("update test city");
        addressUpdateRequestDto.setZipcode("update test zipcode");
        addressUpdateRequestDto.setStreet("update test street");
        addressUpdateRequestDto.setComment("update test comment");
        addressUpdateRequestDto.setIsDefaultAddress(false);

        Long updateAddressId = addressService.updateAddress(addressId, addressUpdateRequestDto, sessionDto);

        AddressResponseDto address = addressService.findAddress(updateAddressId, sessionDto);
        assertAll(
                () -> assertThat(address.getCity()).isEqualTo("update test city"),
                () -> assertThat(address.getZipcode()).isEqualTo("update test zipcode"),
                () -> assertThat(address.getStreet()).isEqualTo("update test street"),
                () -> assertThat(address.getComment()).isEqualTo("update test comment"),
                () -> assertThat(address.getIsDefaultAddress()).isEqualTo(false)
        );
    }

    @Test
    @DisplayName("배송지 삭제")
    void deleteAddressTest() {
        MemberSessionDto sessionDto = createSessionDto();

        AddressSaveRequestDto addressSaveRequestDto = createAddress();
        Long addressId = addressService.saveAddress(addressSaveRequestDto, sessionDto);

        Long deletedAddressId = addressService.deleteAddress(addressId, sessionDto);

        assertThrows(AddressNotFoundException.class,
                () -> addressService.findAddress(deletedAddressId, sessionDto));
    }

    private static AddressSaveRequestDto createAddress() {
        AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
        requestDto.setIsDefaultAddress(true);
        requestDto.setCity("test city");
        requestDto.setZipcode("test zipcode");
        requestDto.setStreet("test street");
        requestDto.setComment("test comment");
        return requestDto;
    }

    private MemberSessionDto createSessionDto() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);
        return sessionDto;
    }
}