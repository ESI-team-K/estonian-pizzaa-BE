package com.example.estonianpizzaaBE.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('CUSTOMER') or hasRole('STAFF') or hasRole('DRIVER')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/staff")
  @PreAuthorize("hasRole('STAFF')")
  public String staffAccess() {
    return "staff Board.";
  }

  @GetMapping("/driver")
  @PreAuthorize("hasRole('DRIVER')")
  public String driverAccess() {
    return "Driver Board.";
  }
}
