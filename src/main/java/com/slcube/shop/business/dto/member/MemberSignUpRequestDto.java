package com.slcube.shop.business.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpRequestDto {

    private String email;
    private String username;
    private String password;
}
