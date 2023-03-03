package com.slcube.shop.business.member.dto;

import com.slcube.shop.business.member.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberSessionDto implements Serializable {

    private Long memberId;
    private String loginEmail;
    private String username;

    public MemberSessionDto(Member member) {
        memberId = member.getId();
        loginEmail = member.getEmail();
        username = member.getUsername();
    }
}
