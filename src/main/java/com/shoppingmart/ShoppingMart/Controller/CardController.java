package com.shoppingmart.ShoppingMart.Controller;

import com.shoppingmart.ShoppingMart.Exception.CustomerNotFoundException;
import com.shoppingmart.ShoppingMart.RequestDTO.CardRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.CardResponseDTO;
import com.shoppingmart.ShoppingMart.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add-card")
    public ResponseEntity addCard(@RequestBody CardRequestDTO cardRequestDTO){

        CardResponseDTO cardResponseDTO;
        try{
            cardResponseDTO = cardService.addCard(cardRequestDTO);
        }
        catch (Exception | CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cardResponseDTO,HttpStatus.ACCEPTED);
    }

    // remove card

    // remove all cards for a particular customer id
}
