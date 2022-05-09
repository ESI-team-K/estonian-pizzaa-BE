package com.example.estonianpizzaaBE.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.DriverStatus;
import com.example.estonianpizzaaBE.model.Customer;
import com.example.estonianpizzaaBE.repository.DriverRepository;
import com.example.estonianpizzaaBE.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverService driverService;
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = new ArrayList<Driver>();
        driverRepository.findAll().forEach(drivers::add);
        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getDriversById(@PathVariable(value = "id") Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Driver with id = " + id));
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }


    @PostMapping("/customers/{customerId}/drivers")
    public ResponseEntity<Driver> addDriver(@PathVariable(value = "customerId") Long customerId, @RequestBody Driver driverRequest) {
        Driver driver = customerRepository.findById(customerId).map(customer -> {
            long driverId = driverRequest.getId();

            // driver is existed
            if (driverId != 0L) {
                Driver _driver = driverRepository.findById(driverId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Driver with id = " + driverId));
                customer.addDriver(_driver);
                customerRepository.save(customer);
                return _driver;
            }

            // add and create new Driver
            customer.addDriver(driverRequest);
            return driverRepository.save(driverRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + customerId));
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }
    @PutMapping("/drivers/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable("id") long id, @RequestBody Driver driverRequest) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DriverId " + id + "not found"));
        driver.setDriver_name(driverRequest.getDriver_name());
        return new ResponseEntity<>(driverRepository.save(driver), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}/drivers/{driversId}")
    public ResponseEntity<HttpStatus> deleteDriverFromCustomer(@PathVariable(value = "customerId") Long customerId, @PathVariable(value = "driversId") Long driverId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + customerId));

        customer.removeDriver(customerId);
        customerRepository.save(customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<HttpStatus> deleteDriver(@PathVariable("id") long id) {
        driverRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/driver/{id}/available")
    public void availableDriver(@PathVariable long id)
    {
        driverService.updateDriverStatus(id, DriverStatus.AVAILABLE);
    }

    @PutMapping("/driver/{id}/busy")
    public void busyDriver(@PathVariable long id)
    {
        driverService.updateDriverStatus(id, DriverStatus.BUSY);;
    }

    public void updateDriverStatus(long id, DriverStatus status) 
    {
        driverService.updateDriverStatus(id, status);
    }



}

