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

<<<<<<< HEAD
=======
    // public String getDriver_description() {
    // return advertisement_description;
    // }

    // public void setAdvertisement_description(String advertisement_description) {
    // this.advertisement_description = advertisement_description;
    // }

>>>>>>> e41ac6ef6096df15e1cba6fbbb8e11466639a896
    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "status")
    private DriverStatus status;

<<<<<<< HEAD
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "drivers")
=======
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "drivers")
>>>>>>> e41ac6ef6096df15e1cba6fbbb8e11466639a896
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

    public void setDriver_name(String name) {
        this.name = name;
    }

<<<<<<< HEAD

    public DriverStatus getStatus() {
        return this.status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

=======
>>>>>>> e41ac6ef6096df15e1cba6fbbb8e11466639a896
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
