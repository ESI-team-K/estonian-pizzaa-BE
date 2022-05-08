package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
