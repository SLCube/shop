package com.slcube.shop.business.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListResponseDto {

    private Long reviewId;
    private String reviewer;
    private double reviewRate;
    private String reviewContent;
    private int recommendedCount;
}
