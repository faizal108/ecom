package com.faizal.ecom.model;

import lombok.Data;

@Data
public class AddToCartModel {
    private String userId;
    private String productId;
    private int quantity;
}
