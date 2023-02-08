package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.common.exception.AddressNotRegisterAnymore;
import com.slcube.shop.common.security.WithMockMember;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


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
        MemberContext memberContext = (MemberContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        member = memberContext.getMember();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("배송지 저장")
    void saveAddressTest() {
        AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
        requestDto.setDefaultAddress(true);
        requestDto.setCity("test city");
        requestDto.setZipcode("test zipcode");
        requestDto.setStreet("test street");
        requestDto.setComment("test comment");

        Long saveId = addressService.saveAddress(requestDto, member);
        AddressResponseDto findAddress = addressService.findAddress(saveId, member);

        assertAll(
                () -> assertThat(findAddress.getCity()).isEqualTo("test city"),
                () -> assertThat(findAddress.getZipcode()).isEqualTo("test zipcode"),
                () -> assertThat(findAddress.getStreet()).isEqualTo("test street"),
                () -> assertThat(findAddress.getComment()).isEqualTo("test comment"),
                () -> assertThat(findAddress.isDefaultAddress()).isEqualTo(true)
        );
    }

    @Test
    @DisplayName("배송지가 3개일때 배송지 추가 시도")
    void addressNotRegisterAnymoreTest() {
        for (int i = 0; i < 3; i++) {
            AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
            requestDto.setDefaultAddress(false);
            requestDto.setCity("test city " + i);
            requestDto.setZipcode("test zipcode " + i);
            requestDto.setStreet("test street " + i);
            requestDto.setComment("test comment " + i);
            addressService.saveAddress(requestDto, member);
        }

        AddressSaveRequestDto fourthAddress = new AddressSaveRequestDto();
        fourthAddress.setCity("fourth address city");
        fourthAddress.setZipcode("fourth address zipcode");
        fourthAddress.setStreet("fourth address street");

        assertThrows(AddressNotRegisterAnymore.class,
                () -> addressService.saveAddress(fourthAddress, member));
    }

    @Test
    @DisplayName("배송지 수정")
    void updateAddressTest() {

    }

    @Test
    @DisplayName("배송지 삭제")
    void deleteAddressTest() {

    }
}