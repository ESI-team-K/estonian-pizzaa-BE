package com.example.estonianpizzaaBE.repository;

import java.util.List;

import com.example.estonianpizzaaBE.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}
