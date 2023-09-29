package com.faizal.ecom.service;

import com.faizal.ecom.dao.PasswordDao;
import com.faizal.ecom.dao.UserDao;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.faizal.ecom.entity.Address;
import com.faizal.ecom.entity.Password;
import com.faizal.ecom.entity.User;
import com.faizal.ecom.util.CommanUtil;
import com.faizal.ecom.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordDao passwordDao;

    @Override
    public ResponseModel addUser(UserRegistrationModel userModel) {
        try {
//            Validate if Phone no Already exist
            User isUserExist = userDao.findByPhone(userModel.getPhone());
            if(isUserExist == null) {
                User user = new User(userModel.getFirstName(), userModel.getLastName(), userModel.getPhone());
                Address address = new Address(userModel.getAddress(), user);
                Password password = new Password(userModel.getPassword(), user);
                userDao.addUser(user, address, password);
                return CommanUtil.prepareOkResponse(Message.REGISTER_SUCCESS, null);
            }
            else {
                return CommanUtil.prepareOkResponse(Message.ALREADY_EXIST,null);
            }
        }catch (Exception e){
            LOG.error("Exception In User Registration : " + e.getMessage());
            return CommanUtil.prepareErrorResponse(Message.REGISTER_FAIL, null);
        }
    }

    @Override
    public ResponseModel updateUser(UpdateUserModel updateUserModel){
        return null;
    }
    @Override
    public ResponseModel setOtp(String phone) {
//        Get generated Otp
        int otpValue = generateOtp();

//      Find user and set otp to given user.
        User user = userDao.findByPhone(phone);
        if(user != null){
            user.setVerificationOTP(otpValue);
        }else{
            return CommanUtil.prepareErrorResponse("User not found",null);
        }

//        Save otp in DB
        userDao.updateUser(user);

        return CommanUtil.prepareOkResponse(Message.SUCCESS,String.valueOf(otpValue));
    }

    @Override
    public ResponseModel verifyOtp(UserVerifyOtpModel user){
//        Get User from the DB and Check exist or not.
//        Also check the provided and stored otp are same or not.
        User isUserExist = userDao.findByPhone(user.getPhone());
        if(isUserExist != null && (isUserExist.getVerificationOTP() == user.getOtp())){
            isUserExist.setStatus(Status.ACTIVE);
            userDao.updateUser(isUserExist);
            return CommanUtil.prepareOkResponse(Message.AUTHENTICATION_SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.AUTHENTICATION_FAIL, null);
        }
    }

    @Override
    public ResponseModel forgotPassword(ForgotPassModel forgotPassModel) {
        User user = userDao.findByPhone(forgotPassModel.getPhone());
        if(user != null && user.getVerificationOTP() == forgotPassModel.getOtp() && !passwordDao.isPasswordExist(user.getUid(), forgotPassModel.getNewPassword())){
            passwordDao.addPassword(new Password(forgotPassModel.getNewPassword(), user));
            return CommanUtil.prepareOkResponse(Message.SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.FAIL,null);
        }

    }

    @Override
    public ResponseModel changePassword(ChangePasswordModel changePasswordModel){
        User user = userDao.findById(changePasswordModel.getUserId());
        String activePassword = passwordDao.getActivePasswordOfUser(changePasswordModel.getUserId());
        if(user != null && changePasswordModel.getOldPassword().equals(activePassword) && !passwordDao.isPasswordExist(user.getUid(), changePasswordModel.getNewPassword())){
            passwordDao.addPassword(new Password(changePasswordModel.getNewPassword(), user));
            return CommanUtil.prepareOkResponse(Message.SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.FAIL,null);
        }
    }
    @Override
    public ResponseModel loginUser(UserLoginModel userModel) {
        User user =  userDao.findByPhoneAndStatus(userModel.getPhone());

        if(user != null){
            String password = passwordDao.getActivePasswordOfUser(user.getUid());
            if(password.equals(userModel.getPassword()))
                return CommanUtil.prepareOkResponse(Message.LOGIN_SUCCESS,null);
        }
        return CommanUtil.prepareErrorResponse(Message.LOGIN_FAIL,null);

    }
    private int generateOtp(){
        Random random = new Random();
        return (100000+random.nextInt(900000));
    }
}
