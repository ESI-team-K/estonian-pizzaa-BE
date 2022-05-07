package com.example.estonianpizzaaBE.model;

import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

public class Notification {

    private final long notificationId;
    private final long notifyUserId;
    private final String message;
    private final Instant notifyDateTime;

    public Notification(long notificationId, long notifyUserId, String message) {
        this.notificationId = notificationId;
        this.notifyUserId = notifyUserId;
        this.message = message;
        this.notifyDateTime = Instant.now();
    }
}
