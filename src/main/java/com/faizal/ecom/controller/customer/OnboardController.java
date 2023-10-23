package com.faizal.ecom.controller.customer;

import com.faizal.ecom.model.password.ChangePasswordModel;
import com.faizal.ecom.model.password.ForgotPassModel;
import com.faizal.ecom.model.responce.ResponseModel;
import com.faizal.ecom.model.user.UserLoginModel;
import com.faizal.ecom.model.user.UserRegistrationModel;
import com.faizal.ecom.model.user.UserVerifyOtpModel;
import com.faizal.ecom.model.vendor.VendorRegistrationModel;
import com.faizal.ecom.service.UserService;
import com.faizal.ecom.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/onboard")
public class OnboardController {

    @Autowired
    private UserService userService;
    @Autowired
    private VendorService vendorService;

    /*-------Onboard Service Ping-------*/
    @GetMapping
    public String iAmAlive () {
        return "I Am Alive";
    }

    /*--------------User-----------------*/
    @PostMapping("/user/register")
    public ResponseModel registerUser(@RequestBody UserRegistrationModel user){
        return userService.addUser(user);
    }

    @PostMapping("/vendor/register")
    public ResponseModel registerVendor(@RequestBody VendorRegistrationModel vendor){
        return vendorService.addVendor(vendor);
    }

    /*----------Security & Authentication--------------*/
    @PostMapping("/user/login")
    public ResponseModel loginUser(@RequestBody UserLoginModel user){
        return userService.loginUser(user);
    }
    @PostMapping("/vendor/login")
    public ResponseModel loginVendor(@RequestBody UserLoginModel vendor){
        return vendorService.loginVendor(vendor);
    }


    /*--------For Vendor-------*/
    @GetMapping("/vendor/sendotp/{phone}")
    public ResponseModel sendOtpVendor(@PathVariable String phone){
        return vendorService.setOtp(phone);
    }

    @PostMapping("/vendor/verifyotp")
    public ResponseModel verifyOtpVendor(@RequestBody UserVerifyOtpModel vendor){
        return vendorService.verifyOtp(vendor);
    }

    @PostMapping("/vendor/forgotpassword")
    public ResponseModel forgotPasswordVendor(@RequestBody ForgotPassModel forgotPassModel){
        return vendorService.forgotPassword(forgotPassModel);
    }



    /*----For User-----*/
    @GetMapping("/sendotp/{phone}")
    public ResponseModel sendOtp(@PathVariable String phone){
        return userService.setOtp(phone);
    }

    @PostMapping("/verifyotp")
    public ResponseModel verifyOtp(@RequestBody UserVerifyOtpModel user){
        return userService.verifyOtp(user);
    }

    @PostMapping("/forgotpassword")
    public ResponseModel forgotPassword(@RequestBody ForgotPassModel forgotPassModel){
        return userService.forgotPassword(forgotPassModel);
    }

}
