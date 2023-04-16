package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Convertor.CustomerConvertor;
import com.shoppingmart.ShoppingMart.Model.Cart;
import com.shoppingmart.ShoppingMart.Model.Customer;
import com.shoppingmart.ShoppingMart.Repository.CustomerRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.CustomerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDTO customerRequestDTO){

        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDTO);

        // allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        // set cart in customer
        customer.setCart(cart);

        customerRepository.save(customer);
        return "Congrats !! Welcome to China Market.";
    }
}
