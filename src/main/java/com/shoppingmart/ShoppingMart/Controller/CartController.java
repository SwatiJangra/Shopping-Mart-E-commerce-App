package com.shoppingmart.ShoppingMart.Controller;

import com.shoppingmart.ShoppingMart.Exception.CustomerNotFoundException;
import com.shoppingmart.ShoppingMart.Exception.ProductNotFoundException;
import com.shoppingmart.ShoppingMart.Repository.CartRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.OrderRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.OrderResponseDTO;
import com.shoppingmart.ShoppingMart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/add")
    public String addToCart(@RequestBody OrderRequestDTO orderRequestDto){

        String response = "";
        try{
            response = cartService.addToCart(orderRequestDto);
        } catch (Exception | ProductNotFoundException | CustomerNotFoundException e) {
            return e.getMessage();
        }

        return response;
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId){
        List<OrderResponseDTO> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkout(customerId);
        }
        catch(Exception | CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDtos,HttpStatus.ACCEPTED);
    }
}
