package com.faizal.ecom.service;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.*;
import com.faizal.ecom.model.address.AddressOperationModel;
import com.faizal.ecom.model.password.ChangePasswordModel;
import com.faizal.ecom.model.password.ChangePhoneModel;
import com.faizal.ecom.model.password.ForgotPassModel;
import com.faizal.ecom.model.responce.ResponseModel;
import com.faizal.ecom.model.user.UpdateUserModel;
import com.faizal.ecom.model.user.UserLoginModel;
import com.faizal.ecom.model.user.UserRegistrationModel;
import com.faizal.ecom.model.user.UserVerifyOtpModel;

public interface UserService {

    ResponseModel addUser(UserRegistrationModel user);
    ResponseModel updateUser(UpdateUserModel updateUserModel);

    ResponseModel getAllAddress(String userID);

    ResponseModel addAddress(AddressOperationModel addressOperationModel);
    ResponseModel loginUser(UserLoginModel user);

    ResponseModel setOtp(String phone);

    ResponseModel verifyOtp(UserVerifyOtpModel user);

    ResponseModel forgotPassword(ForgotPassModel forgotPassModel);

    ResponseModel changePassword(ChangePasswordModel changePasswordModel);

    ResponseModel deleteAddress(String id);

    ResponseModel updateAddress(AddressOperationModel addressOperationModel);

    ResponseModel activeAddressById(String id);

    ResponseModel changePhone(ChangePhoneModel changePhoneModel);

    ResponseModel addToCart(AddToCartModel addToCartModel);

    ResponseModel getAllCartItem(String id);

    ResponseModel addToList(AddToWishListModel addToWishListModel);

    ResponseModel deleteWishItem(String id);

    ResponseModel getUserInfo(String id);
}
