package com.shoppingmart.ShoppingMart.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private int productId;

    private int customerId;

    private int requiredQuantity;
}
