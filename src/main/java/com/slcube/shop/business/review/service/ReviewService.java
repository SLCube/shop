package com.slcube.shop.business.review.service;

import com.slcube.shop.business.review.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    public Long saveReview(ReviewSaveRequestDto requestDto);
    public Long deleteReview(Long reviewId);
    public Long recommendReview(Long reviewId);
    public ReviewResponseDto findReview(Long reviewId);
    public Page<ReviewListResponseDto> findReviews(Long itemId, Pageable pageable);
    public Long reportReview(ReportedReviewSaveRequestDto requestDto);
    public Page<ReportedReviewListResponseDto> findReportedReviews(Pageable pageable);
}
