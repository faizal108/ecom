package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.ProductVendorRelationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductVendorRelationDao {

    @Autowired
    private ProductVendorRelationRepo productVendorRelationRepo;

    public void deleteRelation(String id) {
        productVendorRelationRepo.deleteByProduct_pid(id);
    }
}
