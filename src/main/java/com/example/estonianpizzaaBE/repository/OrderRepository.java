package com.example.estonianpizzaaBE.repository;


import com.example.estonianpizzaaBE.model.order.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
