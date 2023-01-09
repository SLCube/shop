package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.review.ReportedReviewListResponseDto;
import com.slcube.shop.business.dto.review.ReviewListResponseDto;
import com.slcube.shop.business.dto.review.ReviewSaveRequestDto;
import com.slcube.shop.business.dto.review.ReviewReportRequestDto;
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
