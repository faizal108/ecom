package com.faizal.ecom.service;

import com.faizal.ecom.dao.*;
import com.faizal.ecom.entity.*;
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
import com.faizal.ecom.util.CommanUtil;
import com.faizal.ecom.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VendorServiceImpl implements VendorService{

    @Autowired
    private VendorDao vendorDao;
    @Autowired
    private VendorPasswordDao vendorPasswordDao;
    @Autowired
    private VendorAddressDao vendorAddressDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductVendorRelationDao productVendorRelationDao;

    @Override
    public ResponseModel addVendor(VendorRegistrationModel vendor) {
        Vendor newVendor = new Vendor(vendor.getFirstName(),vendor.getLastName(),vendor.getPhone(),vendor.getShopName(),vendor.getGstNumber(),vendor.getBusinessDescription());
        VendorAddress address = new VendorAddress(vendor.getAddress(), newVendor);
        VendorPassword password = new VendorPassword(vendor.getPassword(), newVendor);
        vendorDao.addVendor(newVendor, address,password);
        return CommanUtil.prepareOkResponse(Message.ADD_SUCCESS, null);
    }

    @Override
    public ResponseModel loginVendor(UserLoginModel vendorModel) {
        Vendor vendor =  vendorDao.findByPhoneAndStatus(vendorModel.getPhone());

        if(vendor != null){
            String password = vendorPasswordDao.getActivePasswordOfUser(vendor.getVid());
            if(password.equals(vendorModel.getPassword()))
                return CommanUtil.prepareOkResponse(Message.LOGIN_SUCCESS,null);
        }
        return CommanUtil.prepareErrorResponse(Message.LOGIN_FAIL,null);
    }

    @Override
    public ResponseModel updateVendor(UpdateVendorModel updateVendorModel){
        Vendor vendor = vendorDao.findById(updateVendorModel.getId());
        VendorAddress address = vendorAddressDao.findByVendor_vid(updateVendorModel.getId());
        if(vendor != null && address != null){
            vendor.setFirstName(updateVendorModel.getFirstName());
            vendor.setLastName(updateVendorModel.getLastName());

            address.setCountry(updateVendorModel.getAddress().getCountry());
            address.setState(updateVendorModel.getAddress().getState());
            address.setCity(updateVendorModel.getAddress().getCity());
            address.setStreet(updateVendorModel.getAddress().getStreet());
            address.setPincode(updateVendorModel.getAddress().getPincode());
            address.setStatus(Status.ACTIVE);
            vendor.setAddress(address);
            vendor.setBusinessDescription(updateVendorModel.getBusinessDescription());
            vendorDao.updateVendor(vendor);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }

    @Override
    public ResponseModel updatePhone(ChangePhoneModel changePhoneModel) {
        Vendor vendor = vendorDao.findByPhone(changePhoneModel.getOldPhone());
        if(vendor != null){
            vendor.setPhone(changePhoneModel.getNewPhone());
            vendorDao.updateVendor(vendor);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }

    /*--------------Product----------------*/

    private int threshold = 50;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public ResponseModel addProduct(AddProductModel addProductModel){
        Vendor vendor = vendorDao.findById(addProductModel.getVid());
        Product product = new Product(addProductModel.getName(), addProductModel.getDescription(), addProductModel.getPrice(), addProductModel.getStock());
        ProductVendorRelation relation = new ProductVendorRelation(vendor,product);
        productDao.addProduct(product,relation);
        return CommanUtil.prepareOkResponse(Message.SUCCESS, null);
    }

    @Override
    public ResponseModel updateProduct(Product product) {
        Product existingProduct = productDao.getById(product.getPid());
        if(existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStock(product.getStock());
            existingProduct.setPrice(product.getPrice());
            productDao.updateProduct(existingProduct);

            return CommanUtil.prepareOkResponse(Message.PRODUCT_UPDATED_SUCCESS, null);
        }

        return CommanUtil.prepareErrorResponse(Message.PRODUCT_UPDATED_FAIL, null);
    }

    @Override
    public ResponseModel deleteProduct(String id){
        if(productDao.isExistById(id)){
            productDao.deleteProduct(id);
            productVendorRelationDao.deleteRelation(id);
            return CommanUtil.prepareOkResponse(Message.DELETE_SUCCESS,null);
        }
        return CommanUtil.prepareOkResponse(Message.DELETE_FAIL, null);
    }

    @Override
    public ResponseModel getAllProduct(String id) {
        List<Product> productList = productDao.getAll(id);
        if(productList != null){
            return CommanUtil.prepareOkResponse(Message.SUCCESS,productList);
        }

        return CommanUtil.prepareErrorResponse(Message.FAIL, null);
    }

    @Override
    public ResponseModel getProductById(String id) {
        Product product = productDao.getById(id);
        if(product != null){
            return CommanUtil.prepareOkResponse(Message.SUCCESS, product);
        }
        return CommanUtil.prepareErrorResponse(Message.FAIL, null);
    }

    @Override
    public ResponseModel getLowStockProduct(String vId) {
        List<Product> productList = productDao.findAllByVendorAndThreshold(vId,threshold);
        if(productList != null){
            return CommanUtil.prepareOkResponse(Message.SUCCESS, productList);
        }
        return CommanUtil.prepareErrorResponse(Message.FAIL, null);
    }

    @Override
    public ResponseModel getLowStockProductCustom(IdValueModel idValueModel) {
        List<Product> productList = productDao.findAllByVendorAndThreshold(idValueModel.getId(),idValueModel.getValue());
        if(productList != null){
            return CommanUtil.prepareOkResponse(Message.SUCCESS, productList);
        }
        return CommanUtil.prepareErrorResponse(Message.FAIL, null);
    }

    @Override
    public ResponseModel updateProductStock(IdValueModel idValueModel) {
        Product product = productDao.getById(idValueModel.getId());
        if(product != null){
            product.setStock(product.getStock() + idValueModel.getValue());
            productDao.updateProduct(product);
            return CommanUtil.prepareOkResponse(Message.UPDATE_SUCCESS, null);
        }
        return CommanUtil.prepareErrorResponse(Message.UPDATE_FAIL, null);
    }



    /*-----Verification-------*/

    @Override
    public ResponseModel setOtp(String phone) {
        //Get generated Otp
        int otpValue = generateOtp();

        //Find vendor and set otp to given vendor.
        Vendor vendor = vendorDao.findByPhone(phone);
        if(vendor != null){
            vendor.setVerificationOTP(otpValue);
        }else{
            return CommanUtil.prepareErrorResponse("User not found",null);
        }

        //Save otp in DB
        vendorDao.updateVendor(vendor);

        return CommanUtil.prepareOkResponse(Message.SUCCESS,String.valueOf(otpValue));
    }

    @Override
    public ResponseModel verifyOtp(UserVerifyOtpModel vendor){
//        Get Vendor from the DB and Check exist or not.
//        Also check the provided and stored otp are same or not.
        Vendor isVendorExist = vendorDao.findByPhone(vendor.getPhone());
        if(isVendorExist != null && (isVendorExist.getVerificationOTP() == vendor.getOtp())){
            isVendorExist.setStatus(Status.ACTIVE);
            vendorDao.updateVendor(isVendorExist);
            return CommanUtil.prepareOkResponse(Message.AUTHENTICATION_SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.AUTHENTICATION_FAIL, null);
        }
    }

    @Override
    public ResponseModel forgotPassword(ForgotPassModel forgotPassModel) {
        Vendor vendor = vendorDao.findByPhone(forgotPassModel.getPhone());
        if(vendor != null && vendor.getVerificationOTP() == forgotPassModel.getOtp() && !vendorPasswordDao.isPasswordExist(vendor.getVid(), forgotPassModel.getNewPassword())){
            vendorPasswordDao.addPassword(new VendorPassword(forgotPassModel.getNewPassword(), vendor));
            return CommanUtil.prepareOkResponse(Message.SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.FAIL,null);
        }

    }

    @Override
    public ResponseModel changePassword(ChangePasswordModel changePasswordModel){
        Vendor vendor = vendorDao.findById(changePasswordModel.getUserId());
        String activePassword = vendorPasswordDao.getActivePasswordOfUser(changePasswordModel.getUserId());
        if(vendor != null && changePasswordModel.getOldPassword().equals(activePassword) && !vendorPasswordDao.isPasswordExist(vendor.getVid(), changePasswordModel.getNewPassword())){
            vendorPasswordDao.addPassword(new VendorPassword(changePasswordModel.getNewPassword(), vendor));
            return CommanUtil.prepareOkResponse(Message.SUCCESS,null);
        }else{
            return CommanUtil.prepareErrorResponse(Message.FAIL,null);
        }
    }

    /*---------Private area---------*/
    private int generateOtp(){
        Random random = new Random();
        return (100000+random.nextInt(900000));
    }
}
