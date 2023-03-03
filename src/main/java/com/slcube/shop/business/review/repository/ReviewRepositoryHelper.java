package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import com.slcube.shop.common.exception.ReviewNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepositoryHelper {

    public Review findByNotDeleted(ReviewRepository reviewRepository, Long reviewId) {
        return reviewRepository.findByNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException());
    }
}
