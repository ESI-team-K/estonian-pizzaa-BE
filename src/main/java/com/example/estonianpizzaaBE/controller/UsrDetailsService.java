// package com.example.estonianpizzaaBE.controller;

// import java.util.Optional;
// import com.example.estonianpizzaaBE.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;


// @Service
// public class UsrDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        Optional<User> user = userRepository.findByName(username);
//        user
//        .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
//                return user.map(UsrDetails::new).get();
//    }
    
// }
