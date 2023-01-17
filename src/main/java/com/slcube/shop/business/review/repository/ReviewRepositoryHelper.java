package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepositoryHelper {

    // ItemRepositoryHelper 가 반복되는거처럼 ReviewRepositoryHelper도 반복되고있다.
    public Review findByNotDeleted(ReviewRepository reviewRepository, Long reviewId) {
        return reviewRepository.findByNotDeleted(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰 정보를 찾을 수 없습니다. id = " + reviewId));
    }
}
