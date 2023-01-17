package com.slcube.shop.business.review.service;

import com.slcube.shop.business.review.dto.ReportedReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReportedReviewSaveRequestDto;
import com.slcube.shop.business.review.dto.ReviewSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    public Long saveReview(ReviewSaveRequestDto requestDto);
    public Long deleteReview(Long reviewId);
    public Long recommendReview(Long reviewId);
    public Page<ReviewListResponseDto> findReviews(Long itemId, Pageable pageable);
    public Long ReportReview(ReportedReviewSaveRequestDto requestDto);
    public Page<ReportedReviewListResponseDto> findReportedReview(Pageable pageable);
}
