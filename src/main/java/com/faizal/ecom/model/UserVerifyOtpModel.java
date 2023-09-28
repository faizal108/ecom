package com.faizal.ecom.model;

import lombok.Data;

@Data
public class UserVerifyOtpModel {
    private String phone;
    private int otp;
}
