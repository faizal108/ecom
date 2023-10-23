package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.vendor.VendorAddressRepo;
import com.faizal.ecom.dao.repos.vendor.VendorPasswordRepo;
import com.faizal.ecom.dao.repos.vendor.VendorRepo;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.Vendor;
import com.faizal.ecom.entity.VendorAddress;
import com.faizal.ecom.entity.VendorPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorDao{
    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private VendorPasswordRepo vendorPasswordRepo;
    @Autowired
    private VendorAddressRepo vendorAddressRepo;

    public Vendor findByPhoneAndStatus(String phone) {
        return vendorRepo.findByPhoneAndStatus(phone, Status.ACTIVE).orElse(null);
    }


    public void addVendor(Vendor vendor, VendorAddress address, VendorPassword password) {
        vendorRepo.save(vendor);
        vendorAddressRepo.save(address);
        vendorPasswordRepo.save(password);
    }

    public Vendor findByPhone(String phone) {
        return vendorRepo.findByPhone(phone).orElse(null);
    }

    public void updateVendor(Vendor vendor) {
        vendorRepo.save(vendor);
    }

    public Vendor findById(String vendorId) {
        return vendorRepo.findById(vendorId).orElse(null);
    }
}
