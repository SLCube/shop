package com.slcube.shop.business.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberChangePasswordRequestDto {

    private String email;
    private String currentPassword;
    private String changedPassword;
}
