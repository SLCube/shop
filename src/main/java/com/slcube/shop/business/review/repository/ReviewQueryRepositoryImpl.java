package com.slcube.shop.business.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.slcube.shop.business.member.domain.QMember.member;
import static com.slcube.shop.business.review.domain.QReview.review;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository{

    private final JPAQueryFactory query;

    @Override
    public List<ReviewListResponseDto> findByItemId(Long itemId, Pageable pageable) {
        return query
                .select(Projections.fields(ReviewListResponseDto.class,
                        review.id.as("reviewId"),
                        member.username.as("reviewer"),
                        review.reviewRate,
                        review.reviewContent,
                        review.recommendCount))
                .from(review)
                .join(member)
                .on(review.memberId.eq(member.id))
                .where(review.item.id.eq(itemId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
