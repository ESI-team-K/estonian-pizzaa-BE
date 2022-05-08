package com.example.estonianpizzaaBE.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_card")
@Getter
@Setter
public class PaymentCard {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String lastNumbers;

    private String expirationDate;

    private String cardHolderName;

    private CardType cardType;

    private String referenceNumber;
}

