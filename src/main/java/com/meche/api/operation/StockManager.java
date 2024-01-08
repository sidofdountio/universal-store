package com.meche.api.operation;

import com.meche.model.Inventory;
import com.meche.model.Purchase;
import com.meche.model.Sale;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface StockManager {
    /**
     * In this project the new price (That correspond for sale price)
     * Will be provide by the owner or mananer.
     *
     * */
    Inventory cmupForPurchase(String label, Purchase purchase);
    List<Inventory>  cmupForSale(String label, List<Sale> sales, List<Inventory> inventoryList);
}
