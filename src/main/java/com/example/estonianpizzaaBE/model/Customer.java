package com.example.estonianpizzaaBE.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers", uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        })
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @NotBlank
    @Column(name = "phone_number")
    public String phone_number;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

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

    public Customer(String username, String email, String password, String phone_number) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        
    }

    // getters and setters

    public void addDriver(com.example.estonianpizzaaBE.model.Driver _driver) {
        this.drivers.add((Driver) _driver);
        _driver.getCustomers().add(this);
    }


    public void removeDriver(long DriverId) {
        Driver driver = this.drivers.stream().filter(t -> getId() == DriverId).findFirst().orElse(null);
        if (driver != null) this.drivers.remove(driver);
        ((com.example.estonianpizzaaBE.model.Driver) driver).getCustomers().remove(this);
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + username + ", phone=" + phone_number + "]";
    }
}
