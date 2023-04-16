package com.shoppingmart.ShoppingMart.Convertor;

import com.shoppingmart.ShoppingMart.Enum.ProductStatus;
import com.shoppingmart.ShoppingMart.Model.Product;
import com.shoppingmart.ShoppingMart.RequestDTO.ProductRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.ProductResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {
    public static Product productRequestDtotoProduct(ProductRequestDTO productRequestDTO){

        return Product.builder()
                .productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .productCategory(productRequestDTO.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDTO productToProductResponseDto(Product product){

        return ProductResponseDTO.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
