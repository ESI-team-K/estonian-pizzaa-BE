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

    private static final String defaultMessage = "Your order is on the way! The delivery person will arrive soon.";

    public void sendNotification(Long userId) {

        Notification _notification = new Notification();
        _notification.setUserId(userId);
        _notification.setMessage(defaultMessage);
        _notification.setNotifyDateTime(Instant.now());
        notificationRepository.save(_notification);
    }
}
