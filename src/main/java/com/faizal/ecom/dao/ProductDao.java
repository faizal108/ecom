package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.ProductRepo;
import com.faizal.ecom.dao.repos.ProductVendorRelationRepo;
import com.faizal.ecom.entity.Product;
import com.faizal.ecom.entity.ProductVendorRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductVendorRelationRepo productVendorRelationRepo;

    public void addProduct(Product product, ProductVendorRelation relation) {
        productRepo.save(product);
        productVendorRelationRepo.save(relation);
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

    public List<Product> getAll(String id) {
        List<ProductVendorRelation> relationsList = productVendorRelationRepo.findAllByVendor_vid(id).orElse(null);
        List<Product> productList = new ArrayList<>();

        if(relationsList != null){
            for(ProductVendorRelation relation : relationsList){
                productList.add(relation.getProduct());
            }
        }
        return productList;
    }

    public Product getById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public void updateProduct(Product existingProduct) {
        productRepo.save(existingProduct);
    }

    public List<Product> findAllByVendorAndThreshold(String vId, int threshold) {
        List<ProductVendorRelation> relationsList = productVendorRelationRepo.findAllByVendor_vidAndProduct_StockLessThan(vId, threshold).orElse(null);
        List<Product> productList = new ArrayList<>();

        if(relationsList != null){
            for(ProductVendorRelation relation : relationsList){
                productList.add(relation.getProduct());
            }
        }
        return productList;
    }
}
