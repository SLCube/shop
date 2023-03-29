package com.slcube.shop.business.member.dto;

import lombok.Getter;

@Getter
public class MemberSignUpRequestDto {

    private String email;
    private String username;
    private String password;
}
