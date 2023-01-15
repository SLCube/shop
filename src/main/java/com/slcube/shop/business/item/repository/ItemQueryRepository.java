package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ItemQueryRepository {

    public List<Item> findByCategoryId(Long categoryId, Pageable pageable);
}
