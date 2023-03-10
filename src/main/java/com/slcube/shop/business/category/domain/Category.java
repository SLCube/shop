package com.slcube.shop.business.category.domain;

import com.slcube.shop.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Builder
    private Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public void updateCategory(String newCategoryName) {
        this.categoryName = newCategoryName;
    }
}
