package com.slcube.shop.common.security.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import com.slcube.shop.business.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        MemberSignUpRequestDto requestDto = new MemberSignUpRequestDto();
        requestDto.setEmail("test@naver.com");
        requestDto.setUsername("test name");
        requestDto.setPassword("test password");

        memberService.signUp(requestDto);
    }

    @Test
    void loadUserByUsernameTest() {
        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername("test@naver.com");

        Member member = memberContext.getMember();

        assertThat(member.getUsername()).isEqualTo("test name");
        assertThat(member.getEmail()).isEqualTo("test@naver.com");
        assertThat(passwordEncoder.matches("test password", member.getPassword())).isEqualTo(true);
    }
}