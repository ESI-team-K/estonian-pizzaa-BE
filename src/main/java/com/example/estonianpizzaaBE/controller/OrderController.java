package com.example.estonianpizzaaBE.controller;

import com.example.estonianpizzaaBE.model.Order;
import com.example.estonianpizzaaBE.model.OrderStatus;
import com.example.estonianpizzaaBE.model.OrderType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

	@PostMapping("/order/create")
	public long createOrder(OrderType type){
        if (type == null) { type = OrderType.DELIVERY; }
        Order newOrder = new Order(OrderStatus.PENDING, type);
        orderService.saveOrder(newOrder);
        return newOrder.getOrderId();
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
