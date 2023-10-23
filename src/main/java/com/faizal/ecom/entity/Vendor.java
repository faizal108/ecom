package com.faizal.ecom.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@Data
@Getter
@Setter
@ToString
@Entity
public class Vendor extends AbstractDomain{

    @Id
    private String vid;

    private String firstName;
    private String lastName;
    private String phone;

    @OneToOne(mappedBy = "vendor")
    @JsonManagedReference
    private VendorAddress address;

    private String shopName;
    private String gstNumber;
    private String businessDescription;

    private int verificationOTP;

    @OneToMany(mappedBy = "vendor")
    @JsonManagedReference
    private List<VendorPassword> password;

    public Vendor(){
        this.vid = UUID.randomUUID().toString();
        this.setCreatedDate(Calendar.getInstance(TimeZone.getDefault()));
        this.setStatus(Status.DEACTIVE);
    }

    public Vendor(String firstName, String lastName, String phone,String shopName,String gstNumber, String businessDescription){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.shopName = shopName;
        this.gstNumber = gstNumber;
        this.businessDescription = businessDescription;
    }
}
