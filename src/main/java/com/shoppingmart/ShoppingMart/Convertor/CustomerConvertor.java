package com.shoppingmart.ShoppingMart.Convertor;

import com.shoppingmart.ShoppingMart.Model.Customer;
import com.shoppingmart.ShoppingMart.RequestDTO.CustomerRequestDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDTO customerRequestDTO){

        return Customer.builder()
                .name(customerRequestDTO.getName())
                .age(customerRequestDTO.getAge())
                .email(customerRequestDTO.getEmail())
                .mobNo(customerRequestDTO.getMobNo())
                .build();
    }
}
