package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Notification;
import com.example.estonianpizzaaBE.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notification/{id}")
    public ResponseEntity<Notification> getById(@PathVariable("id") long id) {
        Notification delivery = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id = " + id));
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAll() {
        List<Notification> noti = new ArrayList<Notification>();
        notificationRepository.findAll().forEach(noti::add);
        if (noti.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(noti, HttpStatus.OK);
    }

    @GetMapping("/notifications/user/{id}")
    public ResponseEntity<List<Notification>> getByUserId(@PathVariable("id") long id) {
        List<Notification> noti = new ArrayList<Notification>();
        notificationRepository.findByUserId(id).forEach(noti::add);
        if (noti.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(noti, HttpStatus.OK);
    }

}