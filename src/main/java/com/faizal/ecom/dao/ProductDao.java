package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    @Autowired
    private ProductRepo productRepo;

}
