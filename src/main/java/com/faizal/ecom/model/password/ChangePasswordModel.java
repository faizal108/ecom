package com.faizal.ecom.model.password;

import lombok.Data;

@Data
public class ChangePasswordModel {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
