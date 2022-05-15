// package com.example.estonianpizzaaBE.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

 

// @CrossOrigin // you need  a CrossOrigin annotation
// @RestController
// public class AuthController {

//   @GetMapping("/authenticate")
//     public List<String> authenticate() {

//         Object principal = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication().getPrincipal();
//         List<String> roles = new ArrayList<>();
//         if (principal instanceof UsrDetails) {
//             UserDetails details = (UsrDetails) principal;
//             for (GrantedAuthority authority: details.getAuthorities())
//                 roles.add(authority.getAuthority());
//         }

//         return roles;
//     }

//     @GetMapping("/")
//     public String publicAPI(){
//     return "Hi, this is a public API";
//     }


//     @GetMapping("/auth")
//     public String authenticatedAPI(){
//         return "Hi, you are authenticated";
//     }

//     @GetMapping("/customer")
//     public String userAPI(){
//         return "Hi, you are a customer/staff";
//     } 
 
//     @GetMapping("/staff")
//     public String adminAPI(){
//         return "Hi, you are a staff";
//     } 

//     @GetMapping("/driver")
//     public String driverAPI(){
//         return "Hi, you are a driver";
//     } 


// }


package com.example.estonianpizzaaBE.controller;

import com.example.estonianpizzaaBE.model.ERole;
import com.example.estonianpizzaaBE.model.Role;
import com.example.estonianpizzaaBE.model.User;
import com.example.estonianpizzaaBE.payload.request.LoginRequest;
import com.example.estonianpizzaaBE.payload.request.SignupRequest;
import com.example.estonianpizzaaBE.payload.response.jwtResponse;
import com.example.estonianpizzaaBE.payload.response.MessageResponse;
import com.example.estonianpizzaaBE.repository.RoleRepository;
import com.example.estonianpizzaaBE.repository.UserRepository;
import com.example.estonianpizzaaBE.security.jwt.JwtUtils;
import com.example.estonianpizzaaBE.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new jwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "staff":
          Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(staffRole);

          break;
        case "driver":
          Role driverRole = roleRepository.findByName(ERole.ROLE_DRIVER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(driverRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}


    

