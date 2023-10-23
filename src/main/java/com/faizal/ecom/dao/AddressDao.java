package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.user.AddressRepo;
import com.faizal.ecom.entity.Address;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDao {

    @Autowired
    private AddressRepo addressRepo;

    public void addAddress(Address address){
//        manageAddressHistory(address.getUser());
        addressRepo.save(address);
    }

    public void activeAddress(Address address){
        manageAddressHistory(address.getUser());
        address.setStatus(Status.ACTIVE);
        addressRepo.save(address);
    }
    public boolean deleteAddressById(String id) {
        boolean isExist = addressRepo.existsById(id);
        if(isExist){
            addressRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Address findById(String id) {
        return addressRepo.findById(id).orElse(null);
    }

    public void updateAddress(Address address) {
        addressRepo.save(address);
    }

    /*-------------Private Section---------------*/

    private void manageAddressHistory(User user){
        List<Address> addressList = addressRepo.findAllByUser_uid(user.getUid());
        System.out.println(addressList.size());
        addressList.stream()
                .filter(address -> address.getStatus() == Status.ACTIVE)
                .forEach(address -> {
                    address.setStatus(Status.DEACTIVE);
                    addressRepo.save(address);
                });
    }

    public List<Address> findAllByUserId(String userID) {
        return addressRepo.findAllByUser_uid(userID);
    }
}
