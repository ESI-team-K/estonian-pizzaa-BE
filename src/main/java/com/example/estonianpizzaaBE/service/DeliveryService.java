package com.example.estonianpizzaaBE.service;

import java.time.Instant;

import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Delivery;
import com.example.estonianpizzaaBE.model.DeliveryStatus;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;
import com.example.estonianpizzaaBE.repository.DeliveryRepository;
import com.example.estonianpizzaaBE.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DriverRepository driverRepository;

    public void updateDeliveryStatus(Long id, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id = " + id));
        delivery.setStatus(status);
        if (status == DeliveryStatus.DELIVERED) {
            delivery.setEndDeliveryTime(Instant.now());
        }
        deliveryRepository.save(delivery);
    }

    public Driver findAvailableDriver() {
        var drivers = driverRepository.findByStatus(DriverStatus.AVAILABLE);
        if (!drivers.isEmpty()) {
            return drivers.get(0);
        }
        return null;
    }
}
