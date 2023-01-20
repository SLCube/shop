package com.slcube.shop.business.member.dto;

import com.slcube.shop.business.member.domain.Member;

public class MemberMapper {

    public static Member toEntity(MemberSignUpRequestDto requestDto) {
        return Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .username(requestDto.getUsername())
                .build();
    }
}
