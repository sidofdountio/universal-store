package com.meche.service;

import com.meche.model.Purchase;
import com.meche.repo.PurchaseRepo;
import com.meche.service.serviceImpl.PurchaseDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@RequiredArgsConstructor
public class PurchaseService implements PurchaseDAO {
    private final PurchaseRepo purchaseRepo;
    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public Purchase getPurchase(Long purchaseId) {
        return purchaseRepo.findById(purchaseId).orElseThrow();
    }

    @Override
    public void deletePurchase(Long purchaseIdToDelete) {

    }

    @Override
    public List<Purchase> findByMonth(Month month) {
        return purchaseRepo.findByMonth(month);
    }

    @Override
    public List<Purchase> PURCHASES() {
        return purchaseRepo.findAll();
    }
}
