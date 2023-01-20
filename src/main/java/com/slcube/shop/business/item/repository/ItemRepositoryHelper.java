package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryHelper {

    public Item findByNotDeleted(ItemRepository itemRepository, Long itemId) {
        return itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품 정보가 없습니다. id = " + itemId));
    }
}
