package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findDriverByCustomersId(Long customerId);

    List<Driver> findByStatus(DriverStatus available);
}