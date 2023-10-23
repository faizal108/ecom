package com.faizal.ecom.entity;

import com.faizal.ecom.model.address.AddressModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Data
@Getter
@Setter
@ToString
@Entity
public class VendorAddress extends AbstractDomain{
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "fk_vendor_id")
    @JsonBackReference
    private Vendor vendor;

    private String country;
    private String state;
    private String city;
    private int pincode;
    private String street;

    public VendorAddress(){
        this.id = UUID.randomUUID().toString();
        this.setStatus(Status.ACTIVE);
    }
    public VendorAddress(AddressModel model, Vendor vendor) {
        this();
        this.country = model.getCountry();
        this.state = model.getState();
        this.city = model.getCity();
        this.pincode = model.getPincode();
        this.street = model.getStreet();
        this.vendor = vendor;
    }
}
