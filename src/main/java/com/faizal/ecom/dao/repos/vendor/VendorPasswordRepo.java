package com.faizal.ecom.dao.repos.vendor;

import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.VendorPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorPasswordRepo extends JpaRepository<VendorPassword, String> {
    List<VendorPassword> findByVendor_vidAndStatus (String vendorId, Status status);

    int countByVendor_vidAndVendorPassword(String vid, String newPassword);

    VendorPassword findByVendor_vid(String vid);
}
