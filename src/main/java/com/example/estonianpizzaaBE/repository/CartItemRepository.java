package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.order.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CartItemRepository extends CrudRepository<CartItem, Long>  {
    
}
