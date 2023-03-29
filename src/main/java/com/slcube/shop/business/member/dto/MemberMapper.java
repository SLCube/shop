package com.slcube.shop.business.member.dto;

import com.slcube.shop.business.member.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberMapper {

    public static Member toEntity(MemberSignUpRequestDto requestDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .username(requestDto.getUsername())
                .build();
    }

    private MemberMapper() {
    }
}
