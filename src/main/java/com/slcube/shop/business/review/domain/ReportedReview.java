package com.slcube.shop.business.review.domain;

import com.slcube.shop.common.domain.BaseEntity;
import com.slcube.shop.business.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReportedReview extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "reported_review_id")
    private Long id;

    private String reportedReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
