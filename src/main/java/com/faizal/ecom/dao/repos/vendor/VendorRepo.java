package com.faizal.ecom.dao.repos.vendor;

import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.User;
import com.faizal.ecom.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, String> {
    Optional<Vendor> findByPhoneAndStatus(String phone, Status status);

    Optional<Vendor> findByPhone(String phone);
}
