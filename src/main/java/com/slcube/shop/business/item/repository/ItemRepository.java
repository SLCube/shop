package com.slcube.shop.business.item.repository;

import com.slcube.shop.business.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemQueryRepository {

    @Query("select i from Item i where i.isDeleted = false and i.id = :id")
    public Optional<Item> findByNotDeleted(@Param("id") Long itemId);
}
