package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}