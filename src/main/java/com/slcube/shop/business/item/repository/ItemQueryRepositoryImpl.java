package com.slcube.shop.business.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slcube.shop.business.item.domain.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.slcube.shop.business.item.domain.QItem.item;
import static com.slcube.shop.business.item.domain.QItemCategory.*;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository{

    private final JPAQueryFactory query;

    @Override
    public List<Item> findByCategory(Long categoryId) {
        return query.selectFrom(item)
                .join(item.itemCategories, itemCategory)
                .where(itemCategory.category.id.eq(categoryId))
                .fetch();
    }
}
