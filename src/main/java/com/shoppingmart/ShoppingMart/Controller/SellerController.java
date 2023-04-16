package com.shoppingmart.ShoppingMart.Controller;

import com.shoppingmart.ShoppingMart.RequestDTO.SellerRequestDTO;
import com.shoppingmart.ShoppingMart.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add-seller")
    public String addSeller(@RequestBody SellerRequestDTO sellerRequestDTO) {

    }
}
