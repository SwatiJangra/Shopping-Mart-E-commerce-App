package com.shoppingmart.ShoppingMart.Repository;

import com.shoppingmart.ShoppingMart.Model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {
}
