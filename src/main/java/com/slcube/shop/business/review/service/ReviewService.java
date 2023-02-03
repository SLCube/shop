package com.slcube.shop.business.review.service;

import com.slcube.shop.business.review.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    public Long saveReview(ReviewSaveRequestDto requestDto);
    public Long deleteReview(Long reviewId);
    public Long recommendReview(Long reviewId);
    public ReviewResponseDto findReview(Long reviewId);
    public Page<ReviewListResponseDto> findAllReviews(Long itemId, Pageable pageable);
    public Long reportReview(ReportedReviewSaveRequestDto requestDto);
    public ReportedReviewResponseDto findReportedReview(Long reportedReviewId);
    public Page<ReportedReviewListResponseDto> findAllReportedReviews(Pageable pageable);
}
