package com.slcube.shop.business.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpRequestDto {

    private String email;
    private String username;
    private String password;
}
