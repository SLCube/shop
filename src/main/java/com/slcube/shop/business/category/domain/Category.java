package com.slcube.shop.business.category.domain;

import com.slcube.shop.business.category.dto.CategoryUpdateRequestDto;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.common.domain.BaseEntity;
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
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    @Builder
    private Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public void updateCategory(CategoryUpdateRequestDto requestDto) {
        this.categoryName = requestDto.getCategoryName();
    }
}
