package com.faizal.ecom.controller.customer;

import com.faizal.ecom.model.*;
import com.faizal.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customer/onboard")
public class OnboardController {

    @Autowired
    private UserService userService;
    @GetMapping
    public String iAmAlive () {
        return "I Am Alive";
    }

    @PostMapping("/register")
    public ResponseModel registerUser(@RequestBody UserRegistrationModel user){
        return userService.addUser(user);
    }

    @PutMapping("/updateuser")
    public ResponseModel updateUser(@RequestBody UpdateUserModel updateUserModel){
        return userService.updateUser(updateUserModel);
    }

    /*-------------Address---------------*/
    @PostMapping("/addaddress")
    public ResponseModel addAddress(@RequestBody AddressOperationModel addressOperationModel){
        return userService.addAddress(addressOperationModel);
    }
    @DeleteMapping("/deleteaddress/{id}")
    public ResponseModel deleteAddress(@PathVariable String id){
        return userService.deleteAddress(id);
    }
    @PutMapping("/updateaddress")
    public ResponseModel updateAddress(@RequestBody AddressOperationModel addressOperationModel){
        return userService.updateAddress(addressOperationModel);
    }
    @GetMapping("/activeaddress/{id}")
    public ResponseModel makeActiveAddress(@PathVariable String id){
        return userService.activeAddressById(id);
    }


    /*----------Security & Authentication--------------*/
    @GetMapping("/login")
    public ResponseModel loginUser(@RequestBody UserLoginModel user){
        return userService.loginUser(user);
    }

    @GetMapping("/sendotp/{phone}")
    public ResponseModel sendOtp(@PathVariable String phone){
        return userService.setOtp(phone);
    }

    @PostMapping("/verifyotp")
    public ResponseModel sendOtp(@RequestBody UserVerifyOtpModel user){
        return userService.verifyOtp(user);
    }

    @PostMapping("/forgotpassword")
    public ResponseModel forgotPassword(@RequestBody ForgotPassModel forgotPassModel){
        return userService.forgotPassword(forgotPassModel);
    }

    @PutMapping("/changepassword")
    public ResponseModel changePassword(@RequestBody ChangePasswordModel changePasswordModel){
        return userService.changePassword(changePasswordModel);
    }
}
