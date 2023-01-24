package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.exception.ItemNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryHelper {

    public Item findByNotDeleted(ItemRepository itemRepository, Long itemId) {
        return itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new ItemNotFoundException());
    }
}
