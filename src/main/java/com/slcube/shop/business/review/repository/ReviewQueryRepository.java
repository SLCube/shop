package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryRepository {

    List<ReviewListResponseDto> findByItemId(Long itemId, Pageable pageable);

    Optional<ReviewResponseDto> findDtoByNotDeleted(Long reviewId);
}
