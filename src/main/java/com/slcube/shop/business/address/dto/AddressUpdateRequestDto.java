package com.slcube.shop.business.address.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressUpdateRequestDto {

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

    @NotBlank
    private String street;

    private String comment;

    @NotNull
    private boolean isDefaultAddress;
}
