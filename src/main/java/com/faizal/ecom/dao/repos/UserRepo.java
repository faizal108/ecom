package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByPhone (String phone);

    Optional<User> findByPhoneAndStatus(String phone, Status status);
}
