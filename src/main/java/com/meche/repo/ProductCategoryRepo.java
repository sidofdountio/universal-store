package com.meche.repo;

import com.meche.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface ProductCategoryRepo extends JpaRepository<ProductCategory,Long> {
    Optional<ProductCategory> findByName(String name);
}
