package com.slcube.shop.business.review.service;

import com.slcube.shop.business.review.dto.ReportedReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReviewReportRequestDto;
import com.slcube.shop.business.review.dto.ReviewSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    public Long saveReview(ReviewSaveRequestDto requestDto);
    public Long deleteReview(Long reviewId);
    public Long recommendReview(Long reviewId);
    public Page<ReviewListResponseDto> findReviews(Long itemId, int pageNo);
    public Long ReportReview(ReviewReportRequestDto requestDto);
    public Page<ReportedReviewListResponseDto> findReportedReview(int pageNo);
}
