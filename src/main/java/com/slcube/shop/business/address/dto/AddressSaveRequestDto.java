package com.slcube.shop.business.address.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressSaveRequestDto {

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

    @NotBlank
    private String street;

    private String comment;

    @NotBlank
    private boolean isDefaultAddress;
}
