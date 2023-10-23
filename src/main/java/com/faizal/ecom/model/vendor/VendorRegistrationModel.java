package com.faizal.ecom.model.vendor;

import com.faizal.ecom.entity.VendorAddress;
import com.faizal.ecom.model.address.AddressModel;
import lombok.Data;

@Data
public class VendorRegistrationModel {
    private String firstName;
    private String lastName;
    private String phone;
    private AddressModel address;
    private String shopName;
    private String gstNumber;
    private String businessDescription;
    private String password;
}
