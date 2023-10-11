package com.faizal.ecom.service;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.*;

public interface UserService {

    ResponseModel addUser(UserRegistrationModel user);
    ResponseModel updateUser(UpdateUserModel updateUserModel);
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

    ResponseModel getAllAddress(String userID);

    ResponseModel addProduct(Product product);

    ResponseModel deleteProduct(String id);

    ResponseModel addToCart(AddToCartModel addToCartModel);

    ResponseModel getAllCartItem(String id);

    ResponseModel addToList(AddToWishListModel addToWishListModel);

    ResponseModel deleteWishItem(String id);
}
