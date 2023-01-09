package com.slcube.shop.business.dto.review;

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
