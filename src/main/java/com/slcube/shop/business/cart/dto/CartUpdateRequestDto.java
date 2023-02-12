package com.slcube.shop.business.cart.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class CartUpdateRequestDto {

    @Min(1)
    private int quantity;
}
