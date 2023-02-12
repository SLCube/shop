package com.slcube.shop.business.item.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ItemUpdateRequestDto {

    @NotBlank
    private Long categoryId;

    @NotBlank
    private String itemName;

    @NotBlank
    private int price;

    @NotBlank
    private int stockQuantity;
}
