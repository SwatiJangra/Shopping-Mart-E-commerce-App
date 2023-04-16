package com.shoppingmart.ShoppingMart.Repository;

import com.shoppingmart.ShoppingMart.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
