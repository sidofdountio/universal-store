package com.meche.api;

import com.meche.model.Product;
import com.meche.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/hair/product")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductApi {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        final List<Product> products = productService.getProducts();

        return new ResponseEntity<>(products, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        final Product products = productService.getProductById(id);
        return new ResponseEntity<>(products, OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product productToSave) {
        if (productToSave.getCode().equals("")){
            String code = UUID.randomUUID().toString().substring(0, 10);
            productToSave.setCode(code);
        }
        final Product product = productService.addProduct(productToSave);
        return new ResponseEntity<>(product, CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> editeProduct(@RequestBody Product productToEdite) {
        return new ResponseEntity<>(productService.updateProduct(productToEdite), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        boolean existProduct = productService.existProduct(id);
        if (!existProduct){
            return new ResponseEntity<>(NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(NO_CONTENT);
    }


}
