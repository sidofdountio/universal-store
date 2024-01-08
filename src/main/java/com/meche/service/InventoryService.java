package com.meche.service;

import com.meche.model.Inventory;
import com.meche.repo.InventoryRepo;
import com.meche.service.serviceImpl.InventoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@RequiredArgsConstructor
public class InventoryService implements InventoryDAO {
   private final InventoryRepo inventoryRepo;

    @Override
    public List<Inventory> INVENTORY_LIST() {
        return inventoryRepo.findAll();
    }

    @Override
    public boolean findByProductName(String productName) {
        boolean exist = inventoryRepo.existsByProductName(productName);
        return exist;
    }

    @Override
    public List<Inventory> getInventories(Sort sort) {
        return inventoryRepo.findAll(sort.ascending());
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @Override
    public List<Inventory> saveSaleInventory(List<Inventory> inventories) {
        return inventoryRepo.saveAll(inventories);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }
}
