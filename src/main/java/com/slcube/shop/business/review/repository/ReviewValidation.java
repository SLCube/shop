package com.slcube.shop.business.review.repository;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewValidation {
    private final ItemRepositoryHelper itemRepositoryHelper;


    public Item validateCreateReview(Long itemId) {
        return itemRepositoryHelper.findByNotDeleted(itemId);
    }
}
