package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.review.ReportedReviewListResponseDto;
import com.slcube.shop.business.dto.review.ReviewListResponseDto;
import com.slcube.shop.business.dto.review.ReviewSaveRequestDto;
import com.slcube.shop.business.dto.review.ReviewReportRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    public Long registerReview(ReviewSaveRequestDto requestDto);
    public Long deleteReview(Long id);
    public Long recommendReview(Long id);
    public Page<ReviewListResponseDto> getReviews(Long itemId, int pageNo);
    public Long ReportReview(ReviewReportRequestDto requestDto);
    public Page<ReportedReviewListResponseDto> getReportedReviews(int pageNo);
}
