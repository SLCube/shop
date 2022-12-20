package com.slcube.shop.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {

    ADMIN("ADMIN"),
    USER("USER");

    private String authority;
}
