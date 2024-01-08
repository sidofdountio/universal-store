package com.meche.repo;

import com.meche.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
}
