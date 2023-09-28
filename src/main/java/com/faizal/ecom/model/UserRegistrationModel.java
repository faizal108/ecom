package com.faizal.ecom.model;

import lombok.Data;

@Data
public class UserRegistrationModel {
    private String firstName;
    private String lastName;
    private String phone;
    private AddressModel address;
    private String password;
}

