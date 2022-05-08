package com.example.estonianpizzaaBE.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Instant created;

    private PaymentMethod method;

    @ManyToOne
    @JoinColumn(name = "payment_card_id")
    private PaymentCard paymentCard;

    private PaymentStatus status;
}

