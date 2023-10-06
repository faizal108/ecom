package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends JpaRepository<WishList, String> {
}
