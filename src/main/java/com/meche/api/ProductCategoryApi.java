package com.meche.api;

import com.meche.model.ProductCategory;
import com.meche.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/hair/product-category")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
public class ProductCategoryApi {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryService.getProductCategory();
    }

    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory)
            throws InterruptedException {
        try {

            ProductCategory savedProductCategory = productCategoryService.save(productCategory);
            return new ResponseEntity(savedProductCategory, CREATED);
        } catch (IllegalStateException e) {
            TimeUnit.SECONDS.sleep(1);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable("id") Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
