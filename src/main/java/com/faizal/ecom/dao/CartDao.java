package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.CartRepo;
import com.faizal.ecom.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartDao {

    @Autowired
    private CartRepo cartRepo;

    public void add(Cart cart) {
        cartRepo.save(cart);
    }

    public List<Cart> getAllCartItem(String id) {

        return cartRepo.findByUser_uid(id);
    }
}
