package com.faizal.ecom.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel {
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseModel(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
