package com.example.estonianpizzaaBE.model;

import java.time.Instant;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "message")
    private String message;

    @Column(name = "notifyDateTime")
    private Instant notifyDateTime;

    public Notification() {
    }

    public Notification(long id, long notifyUserId, String message) {
        this.id = id;
        this.userId = notifyUserId;
        this.message = message;
        this.notifyDateTime = Instant.now();
    }

}
