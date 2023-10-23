package com.faizal.ecom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class VendorPassword extends AbstractDomain{

    @Id
    private String pswId;
    private String vendorPassword;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonBackReference
    private Vendor vendor;

    public VendorPassword(){
        this.pswId = UUID.randomUUID().toString();
        this.setStatus(Status.ACTIVE);
    }
    public VendorPassword(String password, Vendor vendor) {
        this();
        this.vendorPassword = password;
        this.vendor = vendor;
    }
}
