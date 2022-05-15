package com.example.estonianpizzaaBE.model;

public enum DeliveryStatus {
    NOT_READY,
    READY, // Waiting for a delivery person
    DISPATCHED, // Delivery person picked up order
    ARRIVED, // Arrive at the destination
    DELIVERED // Success
}