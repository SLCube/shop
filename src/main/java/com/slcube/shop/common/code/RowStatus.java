package com.slcube.shop.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RowStatus {
    Y("Y"),
    N("N");

    private String rowStatus;
}