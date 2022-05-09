package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;
import com.example.estonianpizzaaBE.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    
    @Autowired 
    private DriverRepository DriverRepository;
    
    public List<Driver> getAllDrivers(){
        List<Driver> drivers = new ArrayList<>();
        DriverRepository.findAll().forEach(drivers::add);
        return drivers;
    }

    public Driver fetchDriverById(Long id)
    {
        return DriverRepository.findById(id).get();
    }

    public void updateDriverStatus(Long id, DriverStatus status)
    {
        Driver driver = fetchDriverById(id);
        driver.setStatus(status);
        DriverRepository.save(driver);
    }
    
}
