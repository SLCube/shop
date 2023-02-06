package com.slcube.shop.business.address.service;

import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private MemberRepository memberRepository;

    private Long memberId;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .email("test@naver.com")
                .password("test password")
                .username("test user name")
                .build();

        memberId = memberRepository.save(member).getId();
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

        Long saveId = addressService.saveAddress(requestDto, memberId);

        AddressResponseDto findAddress = addressService.findAddress(saveId, memberId);

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