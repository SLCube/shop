package com.slcube.shop.business.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static com.slcube.shop.business.category.domain.QCategory.*;
import static com.slcube.shop.business.item.domain.QItem.*;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Item> findByCategoryId(Long categoryId, Pageable pageable) {

        return query.selectFrom(item)
                .innerJoin(item.category, category).fetchJoin()
                .where(
                        category.id.eq(categoryId),
                        item.isDeleted.eq(Boolean.FALSE)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
