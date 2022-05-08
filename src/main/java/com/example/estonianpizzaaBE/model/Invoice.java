package com.example.estonianpizzaaBE.model;

import com.example.estonianpizzaaBE.model.payment.Payment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Instant created;

    @ElementCollection
    @CollectionTable(name = "invoice_items", joinColumns = @JoinColumn(name = "invoice_id"))
    @Column(name = "id")
    private Set<Long> menuItems;

    private float fee;

    private float total;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
