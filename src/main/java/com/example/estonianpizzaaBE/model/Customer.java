package com.example.estonianpizzaaBE.model;
import java.sql.Driver;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "customers")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "phone_number")
    public String phone_number;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "users",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id") })
    private Set<Driver> drivers = new HashSet<>();

    public Customer() {
    }
    public Customer(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }
    // getters and setters

    // public void addDriver(Driver driver) {
    //     this.drivers.add(driver);
    //     driver.getCustomers().add(this);
    // }



    // public void removeDriver(long DriverId) {
    //     Driver driver = this.drivers.stream().filter(t -> t.getId() == DriverId).findFirst().orElse(null);
    //     if (driver != null) this.drivers.remove(driver);
    //     driver.getCustomers().remove(this);
    // }

    // @Override
    // public String toString() {
    //     return "Customer [id=" + id + ", name=" + name + ", phone=" + phone_number + "]";
    // }
}
