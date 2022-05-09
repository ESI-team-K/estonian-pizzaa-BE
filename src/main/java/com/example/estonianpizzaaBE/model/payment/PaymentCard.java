package com.example.estonianpizzaaBE.model.payment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "payment_card")
@Getter
@Setter
public class PaymentCard {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastNumbers;

    private YearMonth expirationDate;

    private String cardHolderName;

    private CardType cardType;

    private String referenceNumber;

}

