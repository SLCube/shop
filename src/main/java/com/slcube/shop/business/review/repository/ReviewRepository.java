package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.isDeleted = false and r.id = :id")
    Optional<Review> findByNotDeleted(@Param("id") Long reviewId);

    @Query("select r from Review r where r.item.id = :itemId and r.isDeleted = false")
    Page<Review> findByItemId(@Param("itemId") Long itemId, Pageable pageable);
}
