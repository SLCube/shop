package com.slcube.shop.common.code;

public enum RowStatus {
    Y("Y"), N("N");

    private final String tableValue;

    RowStatus(String tableValue) {
        this.tableValue = tableValue;
    }

    public String getTableValue() {
        return tableValue;
    }
}
