package com.faizal.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class ProductVendorRelation {

    @Id
    private String id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Product product;

    public ProductVendorRelation(){
        this.id = UUID.randomUUID().toString();
    }

    public ProductVendorRelation(Vendor vendor, Product product){
        this();
        this.vendor = vendor;
        this.product = product;
    }
}
