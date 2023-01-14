package com.slcube.shop.business.item.domain;

import com.slcube.shop.business.category.domain.Category;
import com.slcube.shop.business.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemCategory {

    private ItemCategory(Category category, Item item) {
        this.category = category;
        this.item = item;
    }

    @Id
    @GeneratedValue
    @Column(name = "item_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static ItemCategory createItemCategory(Category category, Item item) {
        return new ItemCategory(category, item);
    }

    protected void addItem(Item item) {
        this.item = item;
    }

    public void updateCategory(Category category) {
        this.category = category;
    }
}
