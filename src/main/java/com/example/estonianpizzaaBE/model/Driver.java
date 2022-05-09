package com.example.estonianpizzaaBE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "status")
    private DriverStatus status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "drivers")
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    public Driver() {
    }


    public long getId() {
        return id;
    }

    public String getDriver_name() {
        return name;
    }


    public void setDriver_name(String name) {
        this.name = name;
    }

    public DriverStatus getStatus() {
        return this.status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
