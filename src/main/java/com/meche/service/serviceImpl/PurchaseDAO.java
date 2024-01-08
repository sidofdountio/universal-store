package com.meche.service.serviceImpl;

import com.meche.model.Purchase;

import java.time.Month;
import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface PurchaseDAO {
    Purchase addPurchase(Purchase purchase);

    Purchase updatePurchase(Purchase purchase);

    Purchase getPurchase(Long purchaseId);

    void deletePurchase(Long purchaseIdToDelete);

    List<Purchase> PURCHASES();

    List<Purchase> findByMonth(Month month);
}
