package com.faizal.ecom.model.password;

import lombok.Data;

@Data
public class ChangePhoneModel {
    private String oldPhone;
    private String newPhone;
}
