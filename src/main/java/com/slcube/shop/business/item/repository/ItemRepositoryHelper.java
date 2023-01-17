package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryHelper {

    // helper class도 반복되고 있다.
    public Item findByNotDeleted(ItemRepository itemRepository, Long itemId) {
        return itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품 정보가 없습니다. id = " + itemId));
    }
}
