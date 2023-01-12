package com.slcube.shop.business.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSaveRequestDto {

    private Long itemId;
    private double reviewRate;
    private String reviewContent;
}
