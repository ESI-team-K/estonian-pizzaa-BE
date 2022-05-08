package com.example.estonianpizzaaBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.estonianpizzaaBE.model.Customer;
import com.example.estonianpizzaaBE.model.Driver;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomersByDriversId(Long CustomerId);
    List<Customer> findByNameContaining(String name);
	List<Driver> findDriverByCustomersId(Long customerId);
    Optional<Customer> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
