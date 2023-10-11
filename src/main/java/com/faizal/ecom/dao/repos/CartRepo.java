package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, String> {
    List<Cart> findByUser_uid(String userId);
}
