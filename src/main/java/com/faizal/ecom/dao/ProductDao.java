package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.ProductRepo;
import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    @Autowired
    private ProductRepo productRepo;

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    public boolean isExistById(String id) {
        return productRepo.existsById(id);
    }

    public Product findByUserId(String productId) {
        return productRepo.findById(productId).orElse(null);
    }

}
