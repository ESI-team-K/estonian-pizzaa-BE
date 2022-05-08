package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.payment.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
