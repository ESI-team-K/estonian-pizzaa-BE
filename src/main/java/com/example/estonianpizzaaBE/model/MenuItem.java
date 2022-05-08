package com.example.estonianpizzaaBE.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuItem {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
}
