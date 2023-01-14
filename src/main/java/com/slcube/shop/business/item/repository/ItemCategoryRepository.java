package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    @Query("select ic from ItemCategory ic where ic.category.id = :categoryId and ic.item.id = :itemId")
    public ItemCategory findByCategoryIdAndItemId(@Param("categoryId") Long categoryId, @Param("itemId") Long itemId);
}
