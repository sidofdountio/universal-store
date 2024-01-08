package com.meche.repo;

import com.meche.model.InvoicePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoicePurchaseRepo extends JpaRepository<InvoicePurchase, Long> {
    @Override
    @Query("SELECT i FROM InvoicePurchase i ORDER BY id")
    List<InvoicePurchase> findAll();
}
