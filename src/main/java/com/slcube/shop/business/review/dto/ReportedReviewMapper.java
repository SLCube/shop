package com.slcube.shop.business.review.dto;

import com.slcube.shop.business.review.domain.ReportedReview;

public class ReportedReviewMapper {

    public static ReportedReview toEntity(ReportedReviewSaveRequestDto requestDto) {
        return ReportedReview.builder()
                .reportedReason(requestDto.getReportedReason())
                .build();
    }
}
