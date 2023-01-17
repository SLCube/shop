package com.slcube.shop.business.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.slcube.shop.business.review.domain.QReview.*;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository{

    private final JPAQueryFactory query;

    @Override
    public List<Review> findByItemId(Long itemId, Pageable pageable) {
        return query.selectFrom(review)
                .where(review.item.id.eq(itemId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
