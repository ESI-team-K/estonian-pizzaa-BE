package com.example.estonianpizzaaBE.model;

import java.time.Instant;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "driverId")
    private long driverId;

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

    public Delivery(long driverId, long estimateDeliveryTime, String recipientName,
            String recipientPhoneNumber, String recipientAddress) {
        this.driverId = driverId;
        this.estimateDeliveryTime = estimateDeliveryTime;
        this.startDeliveryTime = Instant.now();
        this.endDeliveryTime = null;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.status = DeliveryStatus.READY;
    }

    public long getId() {
        return id;
    }

    public long getDriverId() {
        return driverId;
    }

    public long getEstimateDeliveryTime() {
        return estimateDeliveryTime;
    }

    public Instant getStartDeliveryTime() {
        return startDeliveryTime;
    }

    public Instant getEndDeliveryTime() {
        return endDeliveryTime;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
