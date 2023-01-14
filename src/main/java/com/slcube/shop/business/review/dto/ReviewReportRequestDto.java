package com.slcube.shop.business.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReportRequestDto {

    private Long reviewId;
    private String reportedReason;
}
