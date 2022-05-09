package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByOrderId(Long orderId);
}
