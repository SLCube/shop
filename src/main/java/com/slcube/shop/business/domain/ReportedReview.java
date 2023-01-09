package com.slcube.shop.business.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReportedReview extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "reported_review_id")
    private Long id;

    private String reportedReason;
}
