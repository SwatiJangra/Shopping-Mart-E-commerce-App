package com.shoppingmart.ShoppingMart.ResponseDTO;
import com.shoppingmart.ShoppingMart.Enum.ProductCategory;
import com.shoppingmart.ShoppingMart.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private String productName;

    private int price;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}
