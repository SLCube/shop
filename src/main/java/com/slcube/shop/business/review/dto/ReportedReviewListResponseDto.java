package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.ReportedReview;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportedReviewListResponseDto {

    private Long reportedReviewId;
    private Long reviewId;
    private String reportedReason;

    public ReportedReviewListResponseDto(ReportedReview reportedReview) {
        reportedReviewId = reportedReview.getId();
        reviewId = reportedReview.getReview().getId();
        reportedReason = reportedReview.getReportedReason();
    }
}
