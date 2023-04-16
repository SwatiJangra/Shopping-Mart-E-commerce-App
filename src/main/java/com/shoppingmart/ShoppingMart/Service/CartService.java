package com.shoppingmart.ShoppingMart.Service;

import com.shoppingmart.ShoppingMart.Enum.ProductStatus;
import com.shoppingmart.ShoppingMart.Exception.CustomerNotFoundException;
import com.shoppingmart.ShoppingMart.Exception.ProductNotFoundException;
import com.shoppingmart.ShoppingMart.Model.*;
import com.shoppingmart.ShoppingMart.Repository.CustomerRepository;
import com.shoppingmart.ShoppingMart.Repository.ProductRepository;
import com.shoppingmart.ShoppingMart.RequestDTO.OrderRequestDTO;
import com.shoppingmart.ShoppingMart.ResponseDTO.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    OrderedItemService orderedItemService;

    public String addToCart(OrderRequestDTO orderRequestDTO) throws Exception, ProductNotFoundException, CustomerNotFoundException {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDTO.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDTO.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDTO.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Cart cart = customer.getCart();

        int newCost = cart.getCartTotal() + orderRequestDTO.getRequiredQuantity()*product.getPrice();
        cart.setCartTotal(newCost);

        // Add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDTO.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart!!";
    }

    public List<OrderResponseDTO> checkout(int customerId) throws Exception, CustomerNotFoundException {

        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        for(Item item: cart.getItems()){
            OrderedItem order = new OrderedItem();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getOrderedItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            // prepare response dto
            OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                    .productName(item.getProduct().getProductName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDTOs.add(orderResponseDTO);
        }

        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        String text = "Congrats your order with total value "+totalCost+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendavengers@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed from China Market");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDTOs;
    }
}
