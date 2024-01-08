package com.meche.service.serviceImpl;

import com.meche.model.Inventory;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InventoryDAO {
    List<Inventory>INVENTORY_LIST();
    List<Inventory>getInventories(Sort sort);
    Inventory saveInventory(Inventory inventory);
    List<Inventory> saveSaleInventory(List<Inventory> inventories);
    Inventory updateInventory(Inventory inventory);
    boolean findByProductName(String productName);
}
