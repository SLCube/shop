package com.slcube.shop.business.item.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ItemUpdateRequestDto {

    @Min(1)
    private Long categoryId;

    @NotBlank
    private String itemName;

    @Min(1)
    private int price;

    @Min(1)
    private int stockQuantity;
}
