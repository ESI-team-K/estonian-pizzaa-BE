package com.example.estonianpizzaaBE.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Driver;
import com.example.estonianpizzaaBE.model.Customer;
import com.example.estonianpizzaaBE.repository.DriverRepository;
import com.example.estonianpizzaaBE.repository.CustomerRepository;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = new ArrayList<Driver>();
        driverRepository.findAll().forEach(drivers::add);
        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/drivers")
    public ResponseEntity<List<Driver>> getAllDriversByCustomerId(@PathVariable(value = "customerId") Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Not found Customer with id = " + customerId);
        }
        List<Driver> tags = driverRepository.findDriverByCustomersId(customerId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getDriversById(@PathVariable(value = "id") Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Driver with id = " + id));
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driversId}/customers")
    public ResponseEntity<List<Customer>> getAllCustomersByDriversId(@PathVariable(value = "driversId") Long driverId) {
        if (!driverRepository.existsById(driverId)) {
            throw new ResourceNotFoundException("Not found Driver  with id = " + driverId);
        }
        List<Customer> customers = customerRepository.findCustomersByDriversId(driverId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
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
}

