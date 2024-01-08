package com.meche.repo;

import com.meche.model.Inventory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Author sidof
 * @Since 30/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    @Override
    @Query("SELECT i FROM Inventory i  ORDER BY i.id desc ")
    List<Inventory> findAll(Sort sort);

    boolean existsByProductName(String productName);


}
