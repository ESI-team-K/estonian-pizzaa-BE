package com.example.estonianpizzaaBE.repository;

import java.util.List;

import com.example.estonianpizzaaBE.model.Order;
import com.example.estonianpizzaaBE.model.OrderStatus;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    // public List<Order> getAllOrders();
    // public Order fetchOrderById(Long id);
    // public void updateOrderStatus(Long id, OrderStatus status);
    // public void sendCancellationRequest(Long id);
    // public void approveCancellation(Long id);
}
