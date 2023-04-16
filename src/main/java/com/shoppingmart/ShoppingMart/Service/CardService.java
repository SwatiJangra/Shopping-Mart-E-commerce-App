package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Exception.CustomerNotFoundException;
import com.shoppingmart.ShoppingMart.Model.Card;
import com.shoppingmart.ShoppingMart.Model.Customer;
import com.shoppingmart.ShoppingMart.Repository.CustomerRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.CardRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.CardDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.CardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) throws CustomerNotFoundException {

        Customer customer;

        try{
            customer = customerRepository.findById(cardRequestDTO.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        // Make a card object
        Card card = Card.builder()
                .cardNo(cardRequestDTO.getCardNo())
                .cvv(cardRequestDTO.getCvv())
                .cardType(cardRequestDTO.getCardType())
                .customer(customer)
                .build();

        // add the card to current card list of customer
        customer.getCards().add(card);

        customerRepository.save(customer); // save both customer and card

        // prepare Response Dto
        CardResponseDTO cardResponseDto = new CardResponseDTO();
        cardResponseDto.setName(customer.getName());

        // convert every card to cardDto
        List<CardDTO> cardDtoList = new ArrayList<>();
        for(Card card1: customer.getCards()){
            CardDTO cardDto = new CardDTO();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResponseDto.setCardDTOs(cardDtoList);
        return cardResponseDto;

    }
}
