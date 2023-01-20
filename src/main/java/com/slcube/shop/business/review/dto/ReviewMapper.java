package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.Review;

public class ReviewMapper {

    public static Review toEntity(ReviewSaveRequestDto requestDto) {
        return Review.builder()
                .reviewRate(requestDto.getReviewRate())
                .reviewContent(requestDto.getReviewContent())
                .build();
    }
}
