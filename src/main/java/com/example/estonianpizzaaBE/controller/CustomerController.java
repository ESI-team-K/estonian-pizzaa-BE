package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.estonianpizzaaBE.exception.ResourceNotFoundException;
import com.example.estonianpizzaaBE.model.Customer;
import com.example.estonianpizzaaBE.repository.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
//@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    // @GetMapping("/customers")
    // public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String customer_name) {
    //     List<Customer> customers = new ArrayList<Customer>();
    //     if (customer_name == null)
    //         customerRepository.findAll().forEach(customers::add);
    //     else
    //         customerRepository.findByNameContaining(customer_name).forEach(customers::add);
    //     if (customers.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }
    //     return new ResponseEntity<>(customers, HttpStatus.OK);
    // }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer _customer = customerRepository.save(new Customer(customer.getUsername(), customer.getEmail(), customer.getPassword(), customer.getPhone_number()));
        return new ResponseEntity<>(_customer, HttpStatus.CREATED);
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        Customer _customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + id));
        _customer.setUsername(customer.getUsername());
        _customer.setPhone_number(customer.getPhone_number());

        return new ResponseEntity<>(customerRepository.save(_customer), HttpStatus.OK);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
        customerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        customerRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/showCustomers")
    public ModelAndView showCustomers(Model model) {
        List<Customer> customers = this.customerRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("customers",customers);
        return new ModelAndView("showCustomers", params);
    }

}
