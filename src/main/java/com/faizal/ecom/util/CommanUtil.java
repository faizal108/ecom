package com.faizal.ecom.util;

import com.faizal.ecom.model.responce.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommanUtil {

    public static ResponseModel prepareOkResponse (String message, Object data){
        return new ResponseModel(HttpStatus.OK,message,data);
    }
    public static ResponseModel prepareErrorResponse (String message, Object data){
        return new ResponseModel(HttpStatus.BAD_REQUEST,message,data);
    }
}
