package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryRepository {

    public List<Review> findByItemId(Long itemId, Pageable pageable);
}
