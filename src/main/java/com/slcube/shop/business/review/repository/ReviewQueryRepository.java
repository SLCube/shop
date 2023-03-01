package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryRepository {

    List<ReviewListResponseDto> findByItemId(Long itemId, Pageable pageable);
}
