package com.example.estonianpizzaaBE.repository;

import java.util.Optional;

import com.example.estonianpizzaaBE.controller.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
        Optional <User> findByName(String username);
     }
     
    