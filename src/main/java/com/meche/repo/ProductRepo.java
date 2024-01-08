package com.meche.repo;

import com.meche.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author sidof
 * @Since 20/05/2023
 */

public interface ProductRepo extends JpaRepository<Product, Long> {
}
