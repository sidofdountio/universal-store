package com.meche.service.serviceImpl;

import com.meche.model.InvoicePurchase;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoicePurchaseDao {
    List<InvoicePurchase> getInvoicePurchase();

    List<InvoicePurchase> addInvoicePurchase(List<InvoicePurchase> invoicePurchases);
}
