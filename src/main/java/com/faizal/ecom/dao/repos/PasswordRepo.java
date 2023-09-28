package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Password;
import com.faizal.ecom.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepo extends JpaRepository<Password, String> {
    List<Password> findByUser_uidAndStatus (String userID, Status status);
    List<Password> findByUser_uidOrderByCreatedDateAsc(String userID);
    int countByUser_uidAndUserPassword (String userId, String password);
}
