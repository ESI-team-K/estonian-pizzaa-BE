package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
