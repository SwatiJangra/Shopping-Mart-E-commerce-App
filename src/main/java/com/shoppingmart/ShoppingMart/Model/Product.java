package com.shoppingmart.ShoppingMart.Model;

import com.shoppingmart.ShoppingMart.Enum.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    Item item;
}
