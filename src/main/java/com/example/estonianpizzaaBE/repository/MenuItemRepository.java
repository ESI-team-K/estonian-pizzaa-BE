package com.example.estonianpizzaaBE.repository;

import com.example.estonianpizzaaBE.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long>  {
    
}
