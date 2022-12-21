package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.review.ReviewListResponseDto;
import com.slcube.shop.business.dto.review.ReviewSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    public Long registerReview(ReviewSaveRequestDto requestDto);
    public Page<ReviewListResponseDto> getReviews(Long itemId, int pageNo);
    public Long deleteReview(Long id);
}
