package com.faizal.ecom.model.product;

import lombok.Data;

@Data
public class AddProductModel {

    private String vid;
    private String name;
    private String description;
    private double price;
    private int stock;
}
