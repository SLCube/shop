package com.slcube.shop.business.member.dto;

import com.slcube.shop.business.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private String email;
    private String username;

    public MemberResponseDto(Member member) {
        email = member.getEmail();
        username = member.getUsername();
    }
}
