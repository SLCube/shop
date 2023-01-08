package com.slcube.shop.business.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    private Item(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
