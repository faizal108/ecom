package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.responce.ResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
}
