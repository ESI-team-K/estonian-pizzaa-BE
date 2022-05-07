package com.example.estonianpizzaaBE.controller;

import com.example.estonianpizzaaBE.model.Notification;
import com.example.repository.NotificationRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllnotifications() {
        List<Notification> notifications = new ArrayList<Notification>();

        notificationRepository.findAll().forEach(notifications::add);

        if (notifications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable("id") long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow();
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @PostMapping("/notifications")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification _notification = notificationRepository.save(new Notification(0, 0, ""));
        return new ResponseEntity<>(_notification, HttpStatus.CREATED);
    }
}
