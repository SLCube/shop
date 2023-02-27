package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.Review;
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

    public ReviewListResponseDto(Review review) {
        reviewId = review.getId();
        reviewer = review.getMember().getUsername();
        reviewRate = review.getReviewRate();
        reviewContent = review.getReviewContent();
        recommendedCount = review.getRecommendCount();
    }
}
