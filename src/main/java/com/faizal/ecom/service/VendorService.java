package com.faizal.ecom.service;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.password.ChangePasswordModel;
import com.faizal.ecom.model.password.ChangePhoneModel;
import com.faizal.ecom.model.password.ForgotPassModel;
import com.faizal.ecom.model.product.AddProductModel;
import com.faizal.ecom.model.product.IdValueModel;
import com.faizal.ecom.model.responce.ResponseModel;
import com.faizal.ecom.model.user.UserLoginModel;
import com.faizal.ecom.model.user.UserVerifyOtpModel;
import com.faizal.ecom.model.vendor.UpdateVendorModel;
import com.faizal.ecom.model.vendor.VendorRegistrationModel;


public interface VendorService {
    ResponseModel addVendor(VendorRegistrationModel vendor);

    ResponseModel loginVendor(UserLoginModel vendor);

    ResponseModel setOtp(String phone);

    ResponseModel verifyOtp(UserVerifyOtpModel user);

    ResponseModel forgotPassword(ForgotPassModel forgotPassModel);

    ResponseModel changePassword(ChangePasswordModel changePasswordModel);

    ResponseModel addProduct(AddProductModel addProductModel);

    ResponseModel deleteProduct(String id);

    ResponseModel updateVendor(UpdateVendorModel updateVendorModel);

    ResponseModel updatePhone(ChangePhoneModel changePhoneModel);

    ResponseModel getAllProduct(String id);

    ResponseModel getProductById(String id);

    ResponseModel updateProduct(Product product);

    ResponseModel getLowStockProduct(String vId);

    ResponseModel getLowStockProductCustom(IdValueModel idValueModel);

    ResponseModel updateProductStock(IdValueModel idValueModel);
}
