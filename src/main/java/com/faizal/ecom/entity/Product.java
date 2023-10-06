package com.faizal.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

@Data
@Getter
@Setter
@ToString
@Entity
public class Product extends AbstractDomain{

    @Id
    private String pid;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product(){
        this.setCreatedDate(Calendar.getInstance(TimeZone.getDefault()));
        this.pid = UUID.randomUUID().toString();
        this.setStatus(Status.ACTIVE);
    }
    public Product(String name, String description, double price, int stock){
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
