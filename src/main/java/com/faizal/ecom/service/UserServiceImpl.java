package com.faizal.ecom.service;

import com.faizal.ecom.dao.*;
import com.faizal.ecom.entity.*;
import com.faizal.ecom.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.faizal.ecom.util.CommanUtil;
import com.faizal.ecom.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordDao passwordDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private WishListDao wishListDao;

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
        User user = userDao.findById(updateUserModel.getId());
        if(user != null){
            user.setFirstName(updateUserModel.getFirstName());
            user.setLastName(updateUserModel.getLastName());
            userDao.updateUser(user);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }

    @Override
    public ResponseModel changePhone(ChangePhoneModel changePhoneModel) {
        User user = userDao.findByPhone(changePhoneModel.getOldPhone());
        if(user != null && !Objects.equals(changePhoneModel.getOldPhone(), changePhoneModel.getNewPhone())){
            user.setPhone(changePhoneModel.getNewPhone());
            userDao.updateUser(user);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }

    @Override
    public ResponseModel getAllAddress(String userID) {
        User user = userDao.findById(userID);

        if(user != null){
            List<Address> addressList = user.getAddress();
            return CommanUtil.prepareOkResponse(Message.SUCCESS,addressList);
        }
        return CommanUtil.prepareErrorResponse(Message.FAIL,null);
    }


    /*-------Address Services--------------------*/
    @Override
    public ResponseModel addAddress(AddressOperationModel addressOperationModel){
        User user  = userDao.findById(addressOperationModel.getId());
        if(user != null){
            addressDao.addAddress(new Address(addressOperationModel.getAddress(), user));
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL,null);
    }

    @Override
    public ResponseModel deleteAddress(String id) {
        if(addressDao.deleteAddressById(id)){
            return CommanUtil.prepareOkResponse(Message.DELETE_SUCCESS,null);
        }
        return CommanUtil.prepareErrorResponse(Message.DELETE_FAIL, null);
    }

    @Override
    public ResponseModel updateAddress(AddressOperationModel addressOperationModel) {
        Address address = addressDao.findById(addressOperationModel.getId());
        if(address != null){
            address.setCountry(addressOperationModel.getAddress().getCountry());
            address.setState(addressOperationModel.getAddress().getState());
            address.setCity(addressOperationModel.getAddress().getCity());
            address.setStreet(addressOperationModel.getAddress().getStreet());
            address.setPincode(addressOperationModel.getAddress().getPincode());
            addressDao.updateAddress(address);

            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS,null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL,null);
    }

    @Override
    public ResponseModel activeAddressById(String id) {
        Address address = addressDao.findById(id);
        if(address != null){
            addressDao.activeAddress(address);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }



    /*-------Verification and Security---------- */
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

    /*--------------Product---------------*/

    @Override
    public ResponseModel addProduct(Product product){
        productDao.addProduct(product);
        return CommanUtil.prepareOkResponse(Message.SUCCESS, null);
    }

    @Override
    public ResponseModel deleteProduct(String id){
        if(productDao.isExistById(id)){
            productDao.deleteProduct(id);
            return CommanUtil.prepareOkResponse(Message.DELETE_SUCCESS,null);
        }
        return CommanUtil.prepareOkResponse(Message.DELETE_FAIL, null);
    }



    /*----------Add To Cart--------------*/
    @Override
    public ResponseModel addToCart(AddToCartModel addToCartModel) {
        // get the user by id.
        User user = userDao.findById(addToCartModel.getUserId());

        // get the product by id.
        Product product = productDao.findByUserId(addToCartModel.getProductId());

        //check the stock with respect to requested quantity.
        boolean checkStock = addToCartModel.getQuantity() <= product.getStock();

        if(user != null && product != null && checkStock){
            cartDao.add(new Cart(user, product, addToCartModel.getQuantity()));
            return CommanUtil.prepareOkResponse(Message.ADD_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.ADD_FAIL, null);
    }

    @Override
    public ResponseModel getAllCartItem(String id) {
        if(userDao.userExistById(id)){
            List<Cart> cart = cartDao.getAllCartItem(id);
            return CommanUtil.prepareOkResponse(Message.SUCCESS, cart);
        }
        return CommanUtil.prepareErrorResponse(Message.FAIL, null);
    }

    /*------------Add To WishList--------------*/

    @Override
    public ResponseModel addToList(AddToWishListModel addToWishListModel){
        // get the user by id.
        User user = userDao.findById(addToWishListModel.getUserId());

        // get the product by id.
        Product product = productDao.findByUserId(addToWishListModel.getProductId());

        if(user != null && product != null){
            wishListDao.addToList(new WishList(user,product));
            return CommanUtil.prepareOkResponse(Message.ADD_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.ADD_FAIL, null);
    }

    @Override
    public ResponseModel deleteWishItem(String id) {
        if(wishListDao.isExistById(id)){
            wishListDao.deleteItem(id);
            return CommanUtil.prepareOkResponse(Message.DELETE_SUCCESS,null);
        }
        return CommanUtil.prepareOkResponse(Message.DELETE_FAIL, null);
    }


}
