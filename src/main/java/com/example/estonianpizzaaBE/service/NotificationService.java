package com.example.estonianpizzaaBE.service;

import java.time.Instant;

import com.example.estonianpizzaaBE.model.Notification;
import com.example.estonianpizzaaBE.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    private static final String defaultCustomerMessage = "Your order is on the way! The delivery person will arrive soon.";
    private static final String defaultDriverMessage = "Your have an order to deliver";

    public void sendNotification(Long userId, String type) {

        Notification _notification = new Notification();
        _notification.setUserId(userId);
        _notification.setNotifyDateTime(Instant.now());
        if (type == "customer") {
            _notification.setMessage(defaultCustomerMessage);
        } else if (type == "driver") {
            _notification.setMessage(defaultDriverMessage);
        }

        notificationRepository.save(_notification);
    }
}
