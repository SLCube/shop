package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.ReportedReview;
import lombok.Getter;

@Getter
public class ReportedReviewResponseDto {

    private Long reportedReviewId;
    private Long reviewId;
    private String reportedReason;

    public ReportedReviewResponseDto(ReportedReview reportedReview) {
        reportedReviewId = reportedReview.getId();
        reviewId = reportedReview.getReview().getId();
        reportedReason = reportedReview.getReportedReason();
    }
}
