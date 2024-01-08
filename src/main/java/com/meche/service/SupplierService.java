package com.meche.service;

import com.meche.model.Supplier;
import com.meche.repo.SupplierRepo;
import com.meche.service.serviceImpl.SupplierDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SupplierService implements SupplierDao {
    private final SupplierRepo supplierRepo;

    @Override
    public Supplier addSupplier(Supplier supplier) {
        log.info("Saving new supplier {}", supplier);
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long supplierId) {
        return supplierRepo.findById(supplierId).get();
    }

    @Override
    public void deleteSupplier(Long supplierIdDelete) {
        supplierRepo.deleteById(supplierIdDelete);
    }

    @Override
    public List<Supplier> SUPPLIERS() {
        log.info("Fecthing supplier..");
        return supplierRepo.findAll();
    }
}
