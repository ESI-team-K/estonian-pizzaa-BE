package com.example.estonianpizzaaBE.controller;


import java.util.ArrayList;

import com.example.estonianpizzaaBE.model.MenuItem;
import com.example.estonianpizzaaBE.model.Order;
import com.example.estonianpizzaaBE.model.OrderStatus;
import com.example.estonianpizzaaBE.model.OrderType;
import com.example.estonianpizzaaBE.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    static class OrderRequest {
        public OrderType type;
        public ArrayList<MenuItem> shoppingCart;
    }

	@PostMapping("/order/create")
	public long createOrder(@RequestBody OrderRequest orderRequest){
        OrderType type = orderRequest.type;
        ArrayList<MenuItem> cart = orderRequest.shoppingCart;

        if (type == null) { type = OrderType.DELIVERY; }
        Order newOrder = new Order(OrderStatus.PENDING, type, cart);
        orderService.saveOrder(newOrder);
        return newOrder.getOrderId();
	}

    @GetMapping("/order/{id}/")
    public @ResponseBody Order getOrder(@PathVariable long id)
    {
        return orderService.fetchOrderById(id);
    }

    @PutMapping("/order/{id}/confirm")
    public void confirmOrder(@PathVariable long id)
    {
        orderService.updateOrderStatus(id, OrderStatus.CONFIRMED);
    }

    @PutMapping("/order/{id}/cancel")
    public void sendCancellationRequest(@PathVariable long id)
    {
        orderService.sendCancellationRequest(id);;
    }

    @PutMapping("/order/{id}/cancel/approve")
    public void approveCancellationRequest(@PathVariable long id)
    {
        orderService.approveCancellationRequest(id);;
    }

    public void updateOrderStatus(long id, OrderStatus status) // If needed, this is available for any other status updates
    {
        orderService.updateOrderStatus(id, status);
    }


}
