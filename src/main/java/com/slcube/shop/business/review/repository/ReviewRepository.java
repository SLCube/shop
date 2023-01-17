package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends ReviewQueryRepository, JpaRepository<Review, Long> {

    @Query("select r from Review r where r.isDeleted = false and r.id = :id")
    public Optional<Review> findByNotDeleted(@Param("id") Long reviewId);
}
