package com.slcube.shop.business.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberStatus {
    ANONYMOUS_USER("anonymousUser"), MEMBER("member"), EXCLUDED("excluded");

    private String memberStatus;
}
