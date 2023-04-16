package com.shoppingmart.ShoppingMart.ResponseDTO;
import com.shoppingmart.ShoppingMart.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private String cardNo;

    private CardType cardType;
}
