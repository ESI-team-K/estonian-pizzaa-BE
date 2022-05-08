package com.example.estonianpizzaaBE.model;

import java.time.Instant;

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

    public long getId() {
        return notificationId;
    }

    public long getNotifyUserId() {
        return notifyUserId;
    }

    public String getMessage() {
        return message;
    }

    public Instant getNotifyDateTime() {
        return notifyDateTime;
    }
}
