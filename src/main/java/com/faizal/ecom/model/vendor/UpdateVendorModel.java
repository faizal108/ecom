package com.faizal.ecom.model.vendor;

import com.faizal.ecom.entity.VendorAddress;
import com.faizal.ecom.model.address.AddressModel;
import lombok.Data;

@Data
public class UpdateVendorModel {
    private String id;
    private String firstName;
    private String lastName;
    private AddressModel address;
    private String businessDescription;
}
