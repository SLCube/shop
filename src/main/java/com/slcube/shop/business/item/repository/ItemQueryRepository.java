package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;

import java.util.List;

public interface ItemQueryRepository {

    public List<Item> findByCategory(Long categoryId);

}
