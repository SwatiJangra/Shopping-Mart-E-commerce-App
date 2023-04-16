package com.shoppingmart.ShoppingMart.Controller;

import com.shoppingmart.ShoppingMart.Exception.CustomerNotFoundException;
import com.shoppingmart.ShoppingMart.Exception.ProductNotFoundException;
import com.shoppingmart.ShoppingMart.Model.OrderedItem;
import com.shoppingmart.ShoppingMart.RequestDTO.OrderRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.OrderResponseDTO;
import com.shoppingmart.ShoppingMart.Service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordered-item")
public class OrderedItemController {
    @Autowired
    OrderedItemService orderedItemServiceService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDTO orderRequestDto){

        OrderResponseDTO orderResponseDto;
        try{
            orderResponseDto = orderedItemServiceService.placeOrder(orderRequestDto);
        }
        catch (Exception | ProductNotFoundException | CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderResponseDto,HttpStatus.ACCEPTED);
    }
}
