package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Delivery;
import com.example.estonianpizzaaBE.model.DeliveryStatus;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.Order;
import com.example.estonianpizzaaBE.model.OrderStatus;
import com.example.estonianpizzaaBE.repository.DeliveryRepository;
import com.example.estonianpizzaaBE.repository.DriverRepository;
import com.example.estonianpizzaaBE.service.DeliveryService;
import com.example.estonianpizzaaBE.service.NotificationService;

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
    DriverRepository driverRepository;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/delivery/{id}")
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

    @PostMapping("/order/{id}/delivery")
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery, @PathVariable long id) {
        // TODO: can improve by check first if it already create

        Driver _driver = deliveryService.findAvailableDriver();
        // Assume there is always a driver
        long driverId = ((_driver == null) ? 0 : _driver.getId());

        // notificationService.sendNotification(driverId, "driver");
        Delivery _delivery = deliveryRepository
                .save(new Delivery(driverId, id, delivery.getEstimateDeliveryTime(),
                        delivery.getRecipientName(), delivery.getRecipientPhoneNumber(),
                        delivery.getRecipientAddress()));
        return new ResponseEntity<>(_delivery, HttpStatus.CREATED);
    }

    // TODO: can add verify status
    @PutMapping("/order/{id}/outForDelivery")
    public void outForDelivery(@PathVariable long id) {
        Order _order = orderService.fetchOrderById(id);
        Delivery _delivery = deliveryRepository.findByOrderId(id);
        deliveryService.updateDeliveryStatus(_delivery.getId(), DeliveryStatus.DISPATCHED);
        orderService.updateOrderStatus(id, OrderStatus.DELIVERING);
        // notificationService.sendNotification(_order.getCustomerId(), "customer");
    }

    @PutMapping("/order/{id}/delivered")
    public void delivered(@PathVariable long id) {
        Delivery _delivery = deliveryRepository.findByOrderId(id);
        deliveryService.updateDeliveryStatus(_delivery.getId(), DeliveryStatus.DELIVERED);
        orderService.fulfillOrder(id);
    }

    // or delete, since need to assign new driver
    @PutMapping("/order/{id}/rejectDelivery")
    public void rejectDelivery(@PathVariable long id) {
        Delivery _delivery = deliveryRepository.findByOrderId(id);
        deliveryService.updateDeliveryStatus(_delivery.getId(), DeliveryStatus.READY);
        orderService.updateOrderStatus(id, OrderStatus.CONFIRMED);
    }

    // Deliver can be deleted and assign to a new driver
    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        deliveryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deliveries")
    public ResponseEntity<HttpStatus> deleteAll() {
        deliveryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
