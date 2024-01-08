package com.meche.service;

import com.meche.model.Product;
import com.meche.repo.ProductRepo;
import com.meche.service.serviceImpl.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService implements ProductDao {
    private final ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        log.info("Saving new product {}", product);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        log.info("Updating new product {}", product);
        boolean existProduct = existProduct(product.getId());
        if (!existProduct) {
            log.error("Product {} not exist", product.getName());
        }
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        log.info("Fetching product by {}", productId);
        return productRepo.findById(productId).orElseThrow(
                () -> {
                    throw new IllegalStateException("Product not found.");
                }
        );
    }

    public boolean existProduct(Long productId) {
        final boolean existsById = productRepo.existsById(productId);
        if (!existsById) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteProduct(Long productToDelete) {
        log.info("Delete product by {}", productToDelete);
        productRepo.deleteById(productToDelete);
    }

    @Override
    public List<Product> getProducts() {
        log.info("Fetching product");
        return productRepo.findAll();
    }
}