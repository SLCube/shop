package com.slcube.shop.common.config.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RowStatus {

    YES("Y"), NO("N");

    private String rowStatus;
}
