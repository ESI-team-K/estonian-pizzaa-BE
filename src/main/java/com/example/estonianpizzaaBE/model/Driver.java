package com.example.estonianpizzaaBE.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    // public String getDriver_description() {
    // return advertisement_description;
    // }

    // public void setAdvertisement_description(String advertisement_description) {
    // this.advertisement_description = advertisement_description;
    // }

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

    public DriverStatus getStatus() {
        return status;
    }

    // public DriverStatus setStatus(DriverStatus status2) {
    //     return status;
    // }

    public void setDriver_name(String name) {
        this.name = name;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }


    public void setStatus(DriverStatus status2) {
    }
}
