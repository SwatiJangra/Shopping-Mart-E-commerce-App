package com.shoppingmart.ShoppingMart.Controller;

import com.shoppingmart.ShoppingMart.Enum.ProductCategory;
import com.shoppingmart.ShoppingMart.Exception.SellerNotFoundException;
import com.shoppingmart.ShoppingMart.RequestDTO.ProductRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.ProductResponseDTO;
import com.shoppingmart.ShoppingMart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDTO productRequestDto){

        ProductResponseDTO productResponseDto;
        try{
            productResponseDto = productService.addProduct(productRequestDto);
        } catch (Exception | SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{productCategory}")
    public List<ProductResponseDTO> getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory){

        return productService.getProductsByCategory(productCategory);
    }
}
