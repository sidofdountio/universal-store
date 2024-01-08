package com.meche.service.serviceImpl;

import com.meche.model.Product;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
public interface ProductDao {
    Product addProduct(Product product);

    Product updateProduct(Product product);

    Product getProductById(Long ProductId);

    void deleteProduct(Long productToDelete);

    List<Product> getProducts();
}
