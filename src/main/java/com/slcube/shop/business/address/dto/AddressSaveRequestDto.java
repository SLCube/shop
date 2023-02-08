package com.slcube.shop.business.address.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressSaveRequestDto {

    @NotNull
    private String city;

    @NotNull
    private String zipcode;

    @NotNull
    private String street;

    private String comment;

    @NotNull
    private boolean isDefaultAddress;
}
