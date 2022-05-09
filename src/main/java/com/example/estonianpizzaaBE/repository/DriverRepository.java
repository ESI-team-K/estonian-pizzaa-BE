package com.example.estonianpizzaaBE.repository;

// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;

public interface DriverRepository extends JpaRepository<Driver, Long> {
<<<<<<< HEAD
=======
    List<Driver> findDriverByCustomersId(Long customerId);

    List<Driver> findByStatus(DriverStatus available);
>>>>>>> e41ac6ef6096df15e1cba6fbbb8e11466639a896
}