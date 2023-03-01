package com.slcube.shop.business.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.review.dto.ReviewListResponseDto;
import com.slcube.shop.business.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.slcube.shop.business.member.domain.QMember.member;
import static com.slcube.shop.business.review.domain.QReview.review;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

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

    @Override
    public Optional<ReviewResponseDto> findDtoByNotDeleted(Long reviewId) {
        ReviewResponseDto reviewResponseDto = query
                .select(Projections.fields(ReviewResponseDto.class,
                        review.id.as("reviewId"),
                        review.reviewRate,
                        member.username.as("reviewer"),
                        review.reviewContent,
                        review.recommendCount))
                .from(review)
                .innerJoin(member).on(review.memberId.eq(member.id))
                .where(review.id.eq(reviewId)
                        .and(review.isDeleted.eq(Boolean.FALSE)))
                .fetchOne();

        return Optional.ofNullable(reviewResponseDto);
    }
}
