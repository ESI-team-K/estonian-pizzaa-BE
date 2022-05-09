// package com.example.estonianpizzaaBE.controller;

// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping("/api/test")
// public class TestController {
//   @GetMapping("/all")
//   public String allAccess() {
//     return "Public Content.";
//   }

//   @GetMapping("/customer")
//   @PreAuthorize("hasRole('CUSTOMER') or hasRole('KITCHEN') or hasRole('DELIVERY')")
//   public String customerAccess() {
//     return "Customer Content.";
//   }

//   @GetMapping("/dev")
//   @PreAuthorize("hasRole('DELIVERY')")
//   public String deliveryAccess() {
//     return "Delivery Board.";
//   }

//   @GetMapping("/kitchen")
//   @PreAuthorize("hasRole('KITCHEN')")
//   public String kitchenAccess() {
//     return "Kitchen Board.";
//   }
// }
