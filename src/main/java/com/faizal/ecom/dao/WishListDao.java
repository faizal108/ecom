package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.WishListRepo;
import com.faizal.ecom.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishListDao {

    @Autowired
    private WishListRepo wishListRepo;


    public void addToList(WishList wishList) {
        wishListRepo.save(wishList);
    }
}
