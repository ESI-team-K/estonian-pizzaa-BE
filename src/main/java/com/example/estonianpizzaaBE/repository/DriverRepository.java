package com.example.estonianpizzaaBE.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findDriverByCustomersId(Long customerId);

    List<Driver> findByStatus(DriverStatus available);
}