package com.example.estonianpizzaaBE.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.estonianpizzaaBE.model.Notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private static final String defaultMessage = "Your order is on the way! The delivery person will arrive in 10 minutes.";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/notify/{notifyUserId}")
    public ResponseEntity<Notification> sendNotification(@PathVariable("notifyUserId") long notifyUserId) {
        Notification noti = new Notification(counter.incrementAndGet(), notifyUserId, defaultMessage);
        return new ResponseEntity<>(noti, HttpStatus.OK);
    }
}