package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepo extends JpaRepository<Address, String> {
    List<Address> findAllByUser_uid(String uid);
}
