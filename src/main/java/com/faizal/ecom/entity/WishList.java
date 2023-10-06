package com.faizal.ecom.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

@Data
@Setter
@Getter
@ToString
@Entity
public class WishList {

    @Id
    private String wid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    public WishList(){
        this.wid = UUID.randomUUID().toString();
        this.createdDate = Calendar.getInstance(Locale.getDefault());
    }
    public WishList(User user, Product product){
        this();
        this.user = user;
        this.product = product;
    }

}

