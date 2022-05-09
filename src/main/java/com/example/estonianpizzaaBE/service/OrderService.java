package com.example.estonianpizzaaBE.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.model.CancellationRequest;
import com.example.estonianpizzaaBE.model.order.Order;
import com.example.estonianpizzaaBE.model.order.OrderStatus;
import com.example.estonianpizzaaBE.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired 
    private OrderRepository orderRepository;
    
    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public Order fetchOrderById(Long id)
    {
        return orderRepository.findById(id).get();
    }
    
    // public List<MenuItem> fetchCartByOrderId(Long id)
    // {
    //     Order order = orderRepository.findById(id).get();
    //     return order.get
    // }

    public void updateOrderStatus(Long id, OrderStatus status)
    {
        Order order = fetchOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
    
    public void saveOrder(Order order)
    {
        orderRepository.save(order);
    }

    public void sendCancellationRequest(Long id)
    {
        Order order = fetchOrderById(id);
        CancellationRequest req = new CancellationRequest();
        order.setCancellationRequest(req);
        System.out.println(order.getOrderId());
        orderRepository.save(order);
    }

    public void approveCancellationRequest(Long id)
    {
        Order order = fetchOrderById(id);
        order.setEndDate(Instant.now()); // Order ended by cancellation
        CancellationRequest req = order.getCancellationRequest();
        req.setApproved(true);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    public void fulfillOrder(Long id)
    {
        Order order = fetchOrderById(id);
        order.setEndDate(Instant.now()); // Order ended by fulfillment
        order.setStatus(OrderStatus.FULFILLED);
    }
}
