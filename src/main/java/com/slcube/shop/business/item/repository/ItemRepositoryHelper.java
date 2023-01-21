package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

@Component
public class ItemRepositoryHelper {

    public Item findByNotDeleted(ItemRepository itemRepository, Long itemId) {
        return itemRepository.findByNotDeleted(itemId)
                .orElseThrow(() -> new CustomException(ITEMS_NOT_FOUND));
    }
}
