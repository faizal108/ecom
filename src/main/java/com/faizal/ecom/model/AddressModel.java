package com.faizal.ecom.model;

import lombok.Data;

@Data
public class AddressModel {
    private String country;
    private String state;
    private String city;
    private String street;
    private int pincode;
}
