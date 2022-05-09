package com.example.estonianpizzaaBE.model;
import javax.persistence.*;
@Entity
@Table(name = "menuitems")
public class Menuitem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "ingredients")
    public String ingredients;
    @Column(name = "size")
    public long size;
    @Column(name = "price")
    public long price;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }


    public long getSize() {
        return size;
    }

    public long getPrice() {
        return price;
    }

   
}
