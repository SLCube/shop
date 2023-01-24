package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long reviewId;
    private double reviewRate;
    private String reviewerName;
    private String reviewContent;
    private int recommendCount;

    public ReviewResponseDto(Review review) {
        reviewId = review.getId();
        reviewRate = review.getReviewRate();
        // member가 구현되지 않아 member의 정보를 갖고올 수 없다.
//        reviewerName = review.getMember().getUsername();
        reviewContent = review.getReviewContent();
        recommendCount = review.getRecommendCount();
    }
}
