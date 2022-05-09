package com.example.estonianpizzaaBE.model;

import java.time.Instant;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "driverId")
    private long driverId;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "estimateDeliveryTime")
    private long estimateDeliveryTime; // minute

    @Column(name = "startDeliveryTime")
    private Instant startDeliveryTime;

    @Column(name = "endDeliveryTime")
    private Instant endDeliveryTime;

    // TODO: Recipient model /
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;

    @Column(name = "status")
    private DeliveryStatus status;

    public Delivery() {
    }

    public Delivery(long driverId, long orderId, long estimateDeliveryTime, String recipientName,
            String recipientPhoneNumber, String recipientAddress) {
        this.driverId = driverId;
        this.orderId = orderId;
        this.estimateDeliveryTime = estimateDeliveryTime;
        this.startDeliveryTime = Instant.now();
        this.endDeliveryTime = null;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.status = DeliveryStatus.READY;
    }

}
