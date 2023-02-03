package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.ReportedReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportedReviewRepository extends JpaRepository<ReportedReview, Long> {
}
