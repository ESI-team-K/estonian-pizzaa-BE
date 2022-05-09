package com.example.estonianpizzaaBE.model;

public enum DeliveryStatus {
    READY, // Waiting for a delivery person
    DISPATCHED, // Delivery person picked up order
    ARRIVED, // Arrive at the destination
    DELIVERED // Success
}