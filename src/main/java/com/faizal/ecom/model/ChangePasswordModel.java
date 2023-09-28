package com.faizal.ecom.model;

import lombok.Data;

@Data
public class ChangePasswordModel {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
