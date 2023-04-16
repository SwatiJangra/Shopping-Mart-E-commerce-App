package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Repository.SellerRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.SellerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public String addSeller(SellerRequestDTO sellerRequestDTO) {
        sellerRepository.save(sellerRequestDTO);
    }
}
