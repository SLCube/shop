package com.slcube.shop.business.category.repository;

import com.slcube.shop.business.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
