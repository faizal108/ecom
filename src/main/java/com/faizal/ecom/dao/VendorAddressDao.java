package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.vendor.VendorAddressRepo;
import com.faizal.ecom.entity.VendorAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorAddressDao {

    @Autowired
    private VendorAddressRepo vendorAddressRepo;

    public VendorAddress findByVendor_vid(String id) {
        return vendorAddressRepo.findByVendor_vid(id);
    }
}
