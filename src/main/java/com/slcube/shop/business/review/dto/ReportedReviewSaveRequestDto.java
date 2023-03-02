package com.slcube.shop.business.review.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReportedReviewSaveRequestDto {

    private Long reviewId;

    @NotBlank
    private String reportedReason;
}
