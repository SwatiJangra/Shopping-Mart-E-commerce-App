package com.shoppingmart.ShoppingMart.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="orderedItem")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date orderDate;
    private int totalCost;
    private int deliveryCharge;
    private String cardUserForPayment;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orderedItem", cascade = CascadeType.ALL)
    List<Item> items=new ArrayList<>();
}
