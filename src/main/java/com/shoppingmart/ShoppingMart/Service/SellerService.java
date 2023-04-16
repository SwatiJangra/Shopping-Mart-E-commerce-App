package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Convertor.SellerConvertor;
import com.shoppingmart.ShoppingMart.Model.Seller;
import com.shoppingmart.ShoppingMart.Repository.SellerRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.SellerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public String addSeller(SellerRequestDTO sellerRequestDTO) {
        Seller seller= SellerConvertor.SellerRequestDtoToSeller(sellerRequestDTO);
        sellerRepository.save(sellerRequestDTO);
        return "Seller is saved successfully";
    }
}
