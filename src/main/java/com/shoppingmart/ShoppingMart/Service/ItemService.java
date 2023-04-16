package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Exception.ProductNotFoundException;
import com.shoppingmart.ShoppingMart.Model.Item;
import com.shoppingmart.ShoppingMart.Model.Product;
import com.shoppingmart.ShoppingMart.Repository.ProductRepository;
import com.shoppingmart.ShoppingMart.ResponseDTO.ItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDTO viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Sorry! Invalid product id.");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        // map item to product
        product.setItem(item);

        // save both item and product
        productRepository.save(product);

        // prepare the response dto
        ItemResponseDTO itemResponseDto = ItemResponseDTO.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}
