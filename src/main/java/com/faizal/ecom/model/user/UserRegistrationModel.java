package com.faizal.ecom.model.user;

import com.faizal.ecom.model.address.AddressModel;
import lombok.Data;

@Data
public class UserRegistrationModel {
    private String firstName;
    private String lastName;
    private String phone;
    private AddressModel address;
    private String password;
}

