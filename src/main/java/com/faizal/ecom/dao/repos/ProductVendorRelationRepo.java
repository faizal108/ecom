package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.entity.ProductVendorRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVendorRelationRepo extends JpaRepository<ProductVendorRelation, String> {
    void deleteByProduct_pid(String id);
    Optional<List<ProductVendorRelation>> findAllByVendor_vidAndProduct_StockLessThan(String vId, int threshold);


    Optional<List<ProductVendorRelation>> findAllByVendor_vid(String id);
}
