package com.meche.repo;

import com.meche.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    List<Purchase>findByMonth(Month month);
}
