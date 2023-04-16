package com.shoppingmart.ShoppingMart.Repository;

import com.shoppingmart.ShoppingMart.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
