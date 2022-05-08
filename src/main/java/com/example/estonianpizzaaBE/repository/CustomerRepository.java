package com.example.estonianpizzaaBE.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.estonianpizzaaBE.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomersByDriversId(Long CustomerId);
    List<Customer> findByNameContaining(String name);
}
