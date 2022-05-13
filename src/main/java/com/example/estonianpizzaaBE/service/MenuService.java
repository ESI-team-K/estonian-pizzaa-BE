package com.example.estonianpizzaaBE.service;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.model.MenuItem;
import com.example.estonianpizzaaBE.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    
    @Autowired MenuRepository menuRepository;
    
    public List<MenuItem> getMenu(){
        List<MenuItem> menu = new ArrayList<>();
        menuRepository.findAll().forEach(menu::add);
        return menu;
    }
    public void saveMenuItem(MenuItem item)
    {
        menuRepository.save(item);
    }
}
