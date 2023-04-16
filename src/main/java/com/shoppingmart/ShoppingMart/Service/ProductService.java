package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Convertor.ProductConvertor;
import com.shoppingmart.ShoppingMart.Enum.ProductCategory;
import com.shoppingmart.ShoppingMart.Exception.SellerNotFoundException;
import com.shoppingmart.ShoppingMart.Model.Product;
import com.shoppingmart.ShoppingMart.Model.Seller;
import com.shoppingmart.ShoppingMart.Repository.ProductRepository;
import com.shoppingmart.ShoppingMart.Repository.SellerRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.ProductRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDto) throws SellerNotFoundException {

        Seller seller;

        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotFoundException("Invalid Seller Id");
        }

        Product product = ProductConvertor.productRequestDtotoProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);

        // saved the seller and product - parent and child
        sellerRepository.save(seller);

        // prepare response
        ProductResponseDTO productResponseDto = ProductConvertor.productToProductResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDTO> getProductsByCategory(ProductCategory productCategory){

        List<Product> products = productRepository.findAllByProductCategory(productCategory);

        // prepare a list of response dtos
        List<ProductResponseDTO> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            ProductResponseDTO productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;
    }
}
