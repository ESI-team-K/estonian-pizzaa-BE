package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.model.MenuItem;
import com.example.estonianpizzaaBE.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;



    @GetMapping("/menu")
    public @ResponseBody List<MenuItem> getMenu()
    {
        return menuService.getMenu();
    }

    @PostMapping("/menu/save")
    public long saveMenuItem(@RequestBody MenuItem item) {
        menuService.saveMenuItem(item);
        return item.getId();
    }

}
