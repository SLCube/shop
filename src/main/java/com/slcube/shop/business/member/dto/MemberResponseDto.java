package com.slcube.shop.business.member.dto;

import com.slcube.shop.business.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberResponseDto implements Serializable {

    private String email;
    private String username;

    public MemberResponseDto(Member member) {
        email = member.getEmail();
        username = member.getUsername();
    }
}
