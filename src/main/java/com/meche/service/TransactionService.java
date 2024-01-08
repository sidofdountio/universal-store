package com.meche.service;

import com.meche.model.Transaction;
import com.meche.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepo transactionRepo;

    public Transaction save(Transaction transaction) {
        log.info("Transaction {} saved", transaction);
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionRepo.findAll();
    }
}
