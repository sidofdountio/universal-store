package com.meche.service.serviceImpl;

import com.meche.model.Supplier;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface SupplierDao {
    Supplier addSupplier(Supplier supplier);

    Supplier updateSupplier(Supplier supplier);

    Supplier getSupplier(Long supplierId);

    void deleteSupplier(Long supplierIdDelete);

    List<Supplier> SUPPLIERS();
}
