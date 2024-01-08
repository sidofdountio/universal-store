package com.meche.api;

import com.meche.model.Supplier;
import com.meche.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/hair/supplier")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class SupplierApi {
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> supplierList() {
        final List<Supplier> suppliers = supplierService.SUPPLIERS();
        return new ResponseEntity<>(suppliers, OK);
    }

    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplierToVave) {
        final Supplier supplier = supplierService.addSupplier(supplierToVave);
        return new ResponseEntity<>(supplier, CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        Supplier supplier = supplierService.getSupplier(id);
        if(supplier == null){
            return new ResponseEntity<>(NOT_FOUND);
        }
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
