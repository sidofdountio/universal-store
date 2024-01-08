package com.meche.service;

import com.meche.model.InvoicePurchase;
import com.meche.repo.InvoicePurchaseRepo;
import com.meche.service.serviceImpl.InvoicePurchaseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InvoicePurchaseService implements InvoicePurchaseDao {
    private final InvoicePurchaseRepo invoicePurchaseRepo;

    @Override
    public List<InvoicePurchase> getInvoicePurchase() {
        log.info("Fetching purchase invoice");
        return invoicePurchaseRepo.findAll();
    }

    @Override
    public List<InvoicePurchase> addInvoicePurchase(List<InvoicePurchase> invoicePurchases) {
        log.info("Saved purchase invoice");
        return invoicePurchaseRepo.saveAll(invoicePurchases);
    }
}
