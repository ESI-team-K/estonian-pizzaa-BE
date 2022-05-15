package com.example.estonianpizzaaBE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 

@CrossOrigin // you need  a CrossOrigin annotation
@RestController
public class AuthController {

  @GetMapping("/authenticate")
    public List<String> authenticate() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<>();
        if (principal instanceof UsrDetails) {
            UserDetails details = (UsrDetails) principal;
            for (GrantedAuthority authority: details.getAuthorities())
                roles.add(authority.getAuthority());
        }

        return roles;
    }

    @GetMapping("/")
    public String publicAPI(){
    return "Hi, this is a public API";
    }


    @GetMapping("/auth")
    public String authenticatedAPI(){
        return "Hi, you are authenticated";
    }

    @GetMapping("/customer")
    public String userAPI(){
        return "Hi, you are a customer/staff";
    } 
 
    @GetMapping("/staff")
    public String adminAPI(){
        return "Hi, you are a staff";
    } 

    @GetMapping("/driver")
    public String driverAPI(){
        return "Hi, you are a driver";
    } 


}

    

