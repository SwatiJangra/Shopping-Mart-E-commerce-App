package com.shoppingmart.ShoppingMart.ResponseDTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private String productName;

    private Date orderDate;

    private int itemPrice;

    private int quantityOrdered;

    private int totalCost;

    private int deliveryCharge;

    private String cardUsedForPayment;
}
