package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemRepositoryHelper {

    private final ItemRepository itemRepository;

    public Item findByNotDeleted(Long itemId) {
        return itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new ItemNotFoundException());
    }
}
