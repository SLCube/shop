package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;
    private double reviewRate;
    private String reviewer;
    private String reviewContent;
    private int recommendCount;

    public ReviewResponseDto(Review review) {
        reviewId = review.getId();
        reviewRate = review.getReviewRate();
        reviewer = review.getCreatedBy();
        reviewContent = review.getReviewContent();
        recommendCount = review.getRecommendCount();
    }
}
