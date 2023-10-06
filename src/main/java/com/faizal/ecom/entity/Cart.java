package com.faizal.ecom.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

@Data
@Getter
@Setter
@ToString
@Entity
public class Cart {

    @Id
    private String cid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;


    public Cart(){
        this.cid = UUID.randomUUID().toString();
        this.createdDate = Calendar.getInstance(TimeZone.getDefault());
    }
    public Cart(User user, Product product, int quantity){
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}
