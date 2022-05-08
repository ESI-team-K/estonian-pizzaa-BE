package com.example.estonianpizzaaBE.model;

import java.time.Instant;

public class Delivery {

    private final long deliveryId;
    private final long driverId;
    private final long estimateDeliveryTime; // minute
    private final Instant startDeliveryTime;
    private final Instant endDeliveryTime;
    // TODO: Recipient model /
    private final String recipientName;
    private final String recipientPhoneNumber;
    private final String recipientAddress;
    // TODO: Enum
    private final String deliveryStatus;

    public Delivery(long deliveryId, long driverId, long estimateDeliveryTime, String recipientName,
            String recipientPhoneNumber, String recipientAddress) {
        this.deliveryId = deliveryId;
        this.driverId = driverId;
        this.estimateDeliveryTime = estimateDeliveryTime;
        this.startDeliveryTime = Instant.now();
        this.endDeliveryTime = null;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.deliveryStatus = "Waiting for a delivery person";
    }

    public long getId() {
        return deliveryId;
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

    public String getStatus() {
        return deliveryStatus;
    }
}
