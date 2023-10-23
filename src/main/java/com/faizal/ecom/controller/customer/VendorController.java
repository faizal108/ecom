package com.faizal.ecom.controller.customer;

import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.password.ChangePasswordModel;
import com.faizal.ecom.model.password.ChangePhoneModel;
import com.faizal.ecom.model.product.AddProductModel;
import com.faizal.ecom.model.product.IdValueModel;
import com.faizal.ecom.model.product.ThresholdModel;
import com.faizal.ecom.model.responce.ResponseModel;
import com.faizal.ecom.model.vendor.UpdateVendorModel;
import com.faizal.ecom.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    /*------------Vendor Info-----------*/

    @PutMapping("/update")
    public ResponseModel updateVendor(@RequestBody UpdateVendorModel updateVendorModel){
        return vendorService.updateVendor(updateVendorModel);
    }

    @PutMapping("/updatephone")
    public ResponseModel updatePhone(@RequestBody ChangePhoneModel changePhoneModel){
        return vendorService.updatePhone(changePhoneModel);
    }
    /*------------Passowrd--------------*/
    @PutMapping("/vendor/changepassword")
    public ResponseModel changePasswordVendor(@RequestBody ChangePasswordModel changePasswordModel){
        return vendorService.changePassword(changePasswordModel);
    }


    /*------------Product---------------*/

    @PostMapping("/addproduct")
    public ResponseModel addProduct(@RequestBody AddProductModel addProductModel){
        return vendorService.addProduct(addProductModel);
    }
    @PutMapping("/updateproduct")
    public ResponseModel updateProduct(@RequestBody Product product){
        return vendorService.updateProduct(product);
    }
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseModel deleteProduct(@PathVariable String id){
        return vendorService.deleteProduct(id);
    }
    @GetMapping("/getall/{vId}")
    public ResponseModel getAllProducts(@PathVariable String vId){
        return vendorService.getAllProduct(vId);
    }
    @GetMapping("/getbyid/{id}")
    public ResponseModel getProductById(@PathVariable String id){
        return vendorService.getProductById(id);
    }
    @GetMapping("/getlowstock/{vId}")
    public ResponseModel getLowStock(@PathVariable String vId){
        return vendorService.getLowStockProduct(vId);
    }
    @GetMapping("/getlowstockcustom")
    public ResponseModel getLowStockCustom(@RequestBody IdValueModel idValueModel){
        return vendorService.getLowStockProductCustom(idValueModel);
    }
    @PutMapping("/addstock")
    public ResponseModel updateStock(@RequestBody IdValueModel idValueModel){
        return vendorService.updateProductStock(idValueModel);
    }
}
