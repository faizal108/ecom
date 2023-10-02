package com.faizal.ecom.dao.repos;

import com.faizal.ecom.entity.Address;
import com.faizal.ecom.entity.User;
import com.faizal.ecom.model.AddressOperationModel;
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
//
//    private void manageAddressHistory(User user){
//        List<Address> addressList = addressRepo.findAllByUser_uid(user.getUid());
//        if(addressList.size() == 3){
////          Delete the oldest password of user
//            addressRepo.deleteById(addressList.get(0).getAdd_id());
//        }
//    }
}
