package com.shoppingmart.ShoppingMart.Exception;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message){
        super(message);
    }
}
