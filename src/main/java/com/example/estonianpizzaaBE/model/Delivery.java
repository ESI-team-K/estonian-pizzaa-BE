package com.example.estonianpizzaaBE.model;

import java.time.Instant;

public class Delivery {

    private final long deliveryId;

    public Delivery(long deliveryId) {
        this.deliveryId = deliveryId;
        Instant.now();
    }

    public long getId() {
        return deliveryId;
    }

}
