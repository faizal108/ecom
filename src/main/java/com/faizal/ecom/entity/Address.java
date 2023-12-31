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
public class Address extends AbstractDomain{

    @Id
    private String add_id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    @JsonBackReference
    private User user;

    private String country;
    private String state;
    private String city;
    private int pincode;
    private String street;

    public Address(){
        this.add_id = UUID.randomUUID().toString();
        this.setStatus(Status.DEACTIVE);
    }
    public Address(AddressModel model, User user) {
        this();
        this.country = model.getCountry();
        this.state = model.getState();
        this.city = model.getCity();
        this.pincode = model.getPincode();
        this.street = model.getStreet();
        this.user = user;
    }
}
