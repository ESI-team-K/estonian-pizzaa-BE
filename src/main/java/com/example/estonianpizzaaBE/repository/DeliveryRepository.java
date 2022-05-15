package com.example.estonianpizzaaBE.repository;

import java.util.List;

import com.example.estonianpizzaaBE.model.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByOrderId(Long orderId);

    List<Delivery> findByDriverId(Long driverId);
}
