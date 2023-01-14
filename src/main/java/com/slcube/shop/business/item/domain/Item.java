package com.slcube.shop.business.item.domain;

import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import com.slcube.shop.common.domain.BaseEntity;
import com.slcube.shop.business.review.domain.Review;
import com.slcube.shop.common.domain.config.jpa.BooleanToYnConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    private Item(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addCategory(ItemCategory itemCategory) {
        this.itemCategories.add(itemCategory);
        itemCategory.addItem(this);
    }

    public void updateItem(ItemUpdateRequestDto requestDto) {
        this.itemName = requestDto.getItemName();
        this.price = requestDto.getPrice();
    }

    public void deleteItem() {
        this.isDeleted = true;
    }
}
