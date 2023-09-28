package com.faizal.ecom.model;

import com.faizal.ecom.entity.User;
import lombok.Data;

@Data
public class ForgotPassModel {
    private String phone;
    private int otp;
    private String newPassword;
}
