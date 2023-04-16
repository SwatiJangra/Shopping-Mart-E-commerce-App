package com.shoppingmart.ShoppingMart.Convertor;

import com.shoppingmart.ShoppingMart.Model.Seller;
import com.shoppingmart.ShoppingMart.RequestDTO.SellerRequestDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {
    public static Seller SellerRequestDtoToSeller(SellerRequestDTO sellerRequestDTO) {
        return Seller.builder()
                .name(sellerRequestDTO.getName())
                .email(sellerRequestDTO.getEmail())
                .mobNo(sellerRequestDTO.getMobNo())
                .panNo(sellerRequestDTO.getPanNo())
                .build();
    }
}
