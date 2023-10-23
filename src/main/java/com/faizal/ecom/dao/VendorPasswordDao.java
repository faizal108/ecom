package com.faizal.ecom.dao;
import com.faizal.ecom.dao.repos.vendor.VendorPasswordRepo;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.VendorPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorPasswordDao {
    @Autowired
    private VendorPasswordRepo vendorPasswordRepo;


    public String getActivePasswordOfUser(String vid) {
        return vendorPasswordRepo.findByVendor_vidAndStatus(vid, Status.ACTIVE).get(0).getVendorPassword();
    }

    public void addPassword(VendorPassword vendorPassword) {
        VendorPassword passwords = vendorPasswordRepo.findByVendor_vid(vendorPassword.getVendor().getVid());
        vendorPasswordRepo.deleteById(passwords.getPswId());

        vendorPasswordRepo.save(vendorPassword);
    }

    public boolean isPasswordExist(String vid, String newPassword) {
        return vendorPasswordRepo.countByVendor_vidAndVendorPassword(vid, newPassword) != 0;
    }
}
