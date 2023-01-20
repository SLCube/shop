package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.dto.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.dto.MemberLoginDto;
import com.slcube.shop.business.member.dto.MemberResponseDto;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void signUpTest() {

        // given
        MemberSignUpRequestDto requestDto = createMember();

        // when
        memberService.signUp(requestDto);

        // then
        MemberLoginDto loginDto = new MemberLoginDto();
        loginDto.setEmail("test@naver.com");
        loginDto.setPassword("test password");

        MemberResponseDto responseDto = memberService.login(loginDto);

        assertAll(
                () -> assertThat(responseDto.getUsername()).isEqualTo("test user name"),
                () -> assertThat(responseDto.getEmail()).isEqualTo("test@naver.com")
        );
    }

    @Test
    @DisplayName("비밀번호 변경")
    void changePasswordTest() {

        // given
        MemberSignUpRequestDto requestDto = createMember();
        Long memberId = memberService.signUp(requestDto);

        MemberChangePasswordRequestDto changePasswordRequestDto = new MemberChangePasswordRequestDto();
        changePasswordRequestDto.setEmail("test@naver.com");
        changePasswordRequestDto.setCurrentPassword("test password");
        changePasswordRequestDto.setChangedPassword("test change password");

        // when
        memberService.changePassword(changePasswordRequestDto);

        // then
        MemberLoginDto loginDto = new MemberLoginDto();
        loginDto.setEmail("test@naver.com");
        loginDto.setPassword("test change password");

        MemberResponseDto responseDto = memberService.login(loginDto);

        assertAll(
                () -> assertThat(responseDto.getEmail()).isEqualTo("test@naver.com"),
                () -> assertThat(responseDto.getUsername()).isEqualTo("test user name")
        );
    }

    private static MemberSignUpRequestDto createMember() {
        MemberSignUpRequestDto requestDto = new MemberSignUpRequestDto();
        requestDto.setEmail("test@naver.com");
        requestDto.setUsername("test user name");
        requestDto.setPassword("test password");
        return requestDto;
    }
}