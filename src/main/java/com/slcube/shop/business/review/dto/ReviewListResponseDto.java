package com.slcube.shop.business.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewListResponseDto {

    private Long reviewId;
    private double reviewRate;
    private String reviewContent;
    private int recommendedCount;
}
