package com.faizal.ecom.service;

import com.faizal.ecom.model.*;

public interface UserService {

    ResponseModel addUser(UserRegistrationModel user);

    ResponseModel updateUser(UpdateUserModel updateUserModel);
    ResponseModel loginUser(UserLoginModel user);

    ResponseModel setOtp(String phone);

    ResponseModel verifyOtp(UserVerifyOtpModel user);

    ResponseModel forgotPassword(ForgotPassModel forgotPassModel);

    ResponseModel changePassword(ChangePasswordModel changePasswordModel);
}
