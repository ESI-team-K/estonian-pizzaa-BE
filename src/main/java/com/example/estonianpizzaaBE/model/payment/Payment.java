package com.example.estonianpizzaaBE.model.payment;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Instant created;

    private PaymentMethod method;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_card_id")
    private PaymentCard paymentCard;

    private PaymentStatus status;
}

