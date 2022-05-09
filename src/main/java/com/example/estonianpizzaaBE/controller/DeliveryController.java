package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Delivery;
import com.example.estonianpizzaaBE.model.DeliveryStatus;
import com.example.estonianpizzaaBE.model.order.OrderStatus;
import com.example.estonianpizzaaBE.repository.DeliveryRepository;
import com.example.estonianpizzaaBE.service.DeliveryService;
import com.example.estonianpizzaaBE.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private OrderService orderService;

    @GetMapping("delivery/{id}")
    public ResponseEntity<Delivery> getById(@PathVariable("id") long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id = " + id));
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/deliveries")
    public ResponseEntity<List<Delivery>> getAll() {
        List<Delivery> delivery = new ArrayList<Delivery>();
        deliveryRepository.findAll().forEach(delivery::add);
        if (delivery.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    // TODO: will become order/{id}/delivery
    @PostMapping("/deliveries")
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        Delivery _delivery = deliveryRepository
                .save(new Delivery(delivery.getDriverId(), delivery.getEstimateDeliveryTime(),
                        delivery.getRecipientName(), delivery.getRecipientPhoneNumber(),
                        delivery.getRecipientAddress()));
        return new ResponseEntity<>(_delivery, HttpStatus.CREATED);
    }

    @PutMapping("/delivery/{id}/dispatching")
    public void confirmOrder(@PathVariable long id) {
        deliveryService.updateDeliveryStatus(id, DeliveryStatus.DISPATCHED);
        orderService.updateOrderStatus(id, OrderStatus.DELIVERING);
    }

    // Deliver can be deleted and assign to a new driver
    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        deliveryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @DeleteMapping("/deliveries")
    // public ResponseEntity<HttpStatus> deleteAll() {
    // deliveryRepository.deleteAll();
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
