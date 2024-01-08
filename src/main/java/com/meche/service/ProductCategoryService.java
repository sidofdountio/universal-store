package com.meche.service;

import com.meche.model.ProductCategory;
import com.meche.repo.ProductCategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    public List<ProductCategory>getProductCategory(){
        return productCategoryRepo.findAll();
    }
    public ProductCategory save(ProductCategory productCategory){
        Optional<ProductCategory> productCategoryExist = productCategoryRepo.findByName(productCategory.getName());
        if (productCategoryExist.isPresent()){
            throw new IllegalStateException("");
        }
        return productCategoryRepo.save(productCategory);
    }

    public  void delete(Long id){
        productCategoryRepo.deleteById(id);
    }


}
