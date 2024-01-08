package com.meche.api;

import com.meche.model.Transaction;
import com.meche.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 30/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/hair/transaction")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
public class TransactionApi {
    private final TransactionService  transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionService() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity<>(transactionService.getTransactions(),OK);
    }
}
