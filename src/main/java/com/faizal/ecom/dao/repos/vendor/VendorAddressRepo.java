package com.faizal.ecom.dao.repos.vendor;

import com.faizal.ecom.dao.VendorDao;
import com.faizal.ecom.entity.VendorAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorAddressRepo extends JpaRepository<VendorAddress, String> {
    VendorAddress findByVendor_vid(String id);
}
