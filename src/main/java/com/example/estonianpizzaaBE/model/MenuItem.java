package com.example.estonianpizzaaBE.model;

import javax.persistence.*;

@Entity
@Table(name = "menuitems")
public class MenuItem {

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public void setPrice(long price) {
        this.price = price;
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

    public MenuItem() {
    }

    public MenuItem(long id, String name, String ingredients, long size, long price) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.size = size;
        this.price = price;
    }


}
