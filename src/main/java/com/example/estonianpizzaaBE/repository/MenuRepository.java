package com.example.estonianpizzaaBE.repository;


import com.example.estonianpizzaaBE.model.MenuItem;

import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuItem, Long> {
}
