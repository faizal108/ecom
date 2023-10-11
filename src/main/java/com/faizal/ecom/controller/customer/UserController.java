package com.faizal.ecom.controller.customer;

import com.faizal.ecom.entity.Cart;
import com.faizal.ecom.entity.Product;
import com.faizal.ecom.model.*;
import com.faizal.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String iAmAlive(){
        return "I am alive!!";
    }

    /*---------User--------------------*/
    @PutMapping("/updateuser")
    public ResponseModel updateUser(@RequestBody UpdateUserModel updateUserModel){
        return userService.updateUser(updateUserModel);
    }

    @PutMapping("/updatephone")
    public ResponseModel changePhone(@RequestBody ChangePhoneModel changePhoneModel){
        return userService.changePhone(changePhoneModel);
    }



    /*--------------Address-------------*/

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

    /*------------Product---------------*/

    @PostMapping("/addproduct")
    public ResponseModel addProduct(@RequestBody Product product){
        return userService.addProduct(product);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseModel deleteProduct(@PathVariable String id){
        return userService.deleteProduct(id);
    }

    /*---------Add To Cart-------------*/

    @PostMapping("/addtocart")
    public ResponseModel addToCart(@RequestBody AddToCartModel addToCartModel){
        return userService.addToCart(addToCartModel);
    }

    @GetMapping("/getallcartitem/{id}")
    public ResponseModel getAllCartItem(@PathVariable String id){
        return userService.getAllCartItem(id);
    }

    @PostMapping("/addtowishlist")
    public ResponseModel addToWishList(@RequestBody AddToWishListModel addToWishListModel){
        return userService.addToList(addToWishListModel);
    }
    @DeleteMapping("deletewishitem/{id}")
    public ResponseModel deleteWishItem(@PathVariable String id){
        return userService.deleteWishItem(id);
    }
}
