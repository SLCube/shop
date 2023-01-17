package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.review.domain.ReportedReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportedReviewRepository extends JpaRepository<ReportedReview, Long> {
    // review Entity와 연관성이 있다 생각해서 reportedReview Entity를 하나의 패키지로 묶었는데 뭔가 이상하다.
}
