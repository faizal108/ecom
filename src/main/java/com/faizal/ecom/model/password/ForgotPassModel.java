package com.faizal.ecom.model.password;

import com.faizal.ecom.entity.User;
import lombok.Data;

@Data
public class ForgotPassModel {
    private String phone;
    private int otp;
    private String newPassword;
}
