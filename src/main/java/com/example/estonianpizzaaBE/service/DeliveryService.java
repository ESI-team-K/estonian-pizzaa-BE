package com.example.estonianpizzaaBE.service;

import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Delivery;
import com.example.estonianpizzaaBE.model.DeliveryStatus;
import com.example.estonianpizzaaBE.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public void updateDeliveryStatus(Long id, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id = " + id));
        delivery.setStatus(status);
        deliveryRepository.save(delivery);
    }
}
