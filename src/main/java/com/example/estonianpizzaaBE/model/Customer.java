package com.example.estonianpizzaaBE.model;

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
    @Column(name = "publisher_address")
    public String publisher_address;
    @Column(name = "publisher_contact")
    public String publisher_contact;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPublisher_address() {
        return publisher_address;
    }

    public void setPublisher_address(String publisher_address) {
        this.publisher_address = publisher_address;
    }

    public String getPublisher_contact() {
        return publisher_contact;
    }

    public void setPublisher_contact(String publisher_contact) {
        this.publisher_contact = publisher_contact;
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
    @JoinTable(name = "ads",
            joinColumns = { @JoinColumn(name = "publisher_id") },
            inverseJoinColumns = { @JoinColumn(name = "advertisement_id") })
    private Set<Advertisement> advertisements = new HashSet<>();

    public Customer() {
    }
    public Customer(String name, String publisher_address, String publisher_contact) {
        this.name = name;
        this.publisher_address = publisher_address;
        this.publisher_contact = publisher_contact;
    }
    // getters and setters
    public void addAdvertisement(Advertisement advertisement) {
        this.advertisements.add(advertisement);
        advertisement.getPublishers().add(this);
    }

    public void removeAdvertisement(long AdvertisementId) {
        Advertisement advertisement = this.advertisements.stream().filter(t -> t.getId() == AdvertisementId).findFirst().orElse(null);
        if (advertisement != null) this.advertisements.remove(advertisement);
        advertisement.getPublishers().remove(this);
    }

    @Override
    public String toString() {
        return "Publisher [id=" + id + ", name=" + name + ", address=" + publisher_address + ", contact=" + publisher_contact + "]";
    }
}
