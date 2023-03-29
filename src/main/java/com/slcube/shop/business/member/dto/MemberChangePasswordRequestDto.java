package com.slcube.shop.business.member.dto;

import lombok.Getter;

@Getter
public class MemberChangePasswordRequestDto {

    private String email;
    private String currentPassword;
    private String changedPassword;
}
