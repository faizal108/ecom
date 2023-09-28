package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.AddressRepo;
import com.faizal.ecom.dao.repos.PasswordRepo;
import com.faizal.ecom.dao.repos.UserRepo;
import com.faizal.ecom.entity.Address;
import com.faizal.ecom.entity.Password;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private PasswordRepo passwordRepo;

    public void addUser(User user, Address address, Password password){
        userRepo.save(user);
        passwordRepo.save(password);
        addressRepo.save(address);
    }


    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone).orElse(null);
    }

    public void updateUser(User user){
        userRepo.save(user);
    }

    public User findByPhoneAndStatus(String phone) {
        return userRepo.findByPhoneAndStatus(phone, Status.ACTIVE).orElse(null);
    }

    public User findById(String userId) {
        return userRepo.findById(userId).orElse(null);
    }
}
