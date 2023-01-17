package com.slcube.shop.business.review.dto;

import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long reviewId;
    private double reviewRate;
    private String reviewerName;
    private String reviewContent;
    private int recommendCount;
}
