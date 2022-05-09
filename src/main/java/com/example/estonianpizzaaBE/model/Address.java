package com.example.estonianpizzaaBE.model;
import javax.persistence.*;
@Entity
@Table(name = "addresses")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "house_number")
    public long house_number;
    @Column(name = "street")
    public String street;
    @Column(name = "city")
    public String city;
    @Column(name = "state")
    public String state;
    @Column(name = "postal_code")
    public long postal_code;

    public Address() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHouse_number() {
        return house_number;
    }

    public void setHouse_number(long house_number) {
        this.house_number = house_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String state() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long postal_code() {
        return postal_code;
    }

    public void setPostal_code(long postal_code) {
        this.postal_code = postal_code;
    }



    // @OneToOne(mappedBy = "addresses")
    // private Customer customer;

    public Address(long house_number, String street, String city, String state, long postal_code) {
        this.house_number = house_number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
    }

   
}
