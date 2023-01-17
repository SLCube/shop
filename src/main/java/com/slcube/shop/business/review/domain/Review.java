package com.slcube.shop.business.review.domain;

import com.slcube.shop.common.config.jpa.BooleanToYnConverter;
import com.slcube.shop.common.domain.BaseEntity;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.item.domain.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private double reviewRate;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private int recommendCount = 0;

    @Column(nullable = false)
    @Convert(converter = BooleanToYnConverter.class)
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Review(double reviewRate, String reviewContent) {
        this.reviewRate = reviewRate;
        this.reviewContent = reviewContent;
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public void recommendReview() {
        this.recommendCount++;
    }

    public void deleteReview() {
        this.isDeleted = Boolean.TRUE;
    }
}
