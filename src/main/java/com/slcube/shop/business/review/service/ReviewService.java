package com.slcube.shop.business.review.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.review.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Long saveReview(ReviewSaveRequestDto requestDto, Member member);
    Long deleteReview(Long reviewId);
    Long recommendReview(Long reviewId);
    ReviewResponseDto findReview(Long reviewId);
    Page<ReviewListResponseDto> findAllReviews(Long itemId, Pageable pageable);
    Long reportReview(ReportedReviewSaveRequestDto requestDto);
    ReportedReviewResponseDto findReportedReview(Long reportedReviewId);
    Page<ReportedReviewListResponseDto> findAllReportedReviews(Pageable pageable);
}
