package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import com.slcube.shop.common.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

@Component
public class ReviewRepositoryHelper {

    public Review findByNotDeleted(ReviewRepository reviewRepository, Long reviewId) {
        return reviewRepository.findByNotDeleted(reviewId)
                .orElseThrow(() -> new CustomException(REVIEWS_NOT_FOUND));
    }
}
