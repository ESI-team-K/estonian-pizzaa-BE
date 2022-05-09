// package com.example.estonianpizzaaBE.controller;

// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// import javax.validation.Valid;

// import com.example.estonianpizzaaBE.model.Customer;
// import com.example.estonianpizzaaBE.model.ERole;
// import com.example.estonianpizzaaBE.model.Role;
// import com.example.estonianpizzaaBE.payload.request.LoginRequest;
// import com.example.estonianpizzaaBE.payload.request.SignupRequest;
// import com.example.estonianpizzaaBE.payload.response.MessageResponse;
// import com.example.estonianpizzaaBE.payload.response.jwtResponse;
// import com.example.estonianpizzaaBE.repository.CustomerRepository;
// import com.example.estonianpizzaaBE.repository.RoleRepository;
// import com.example.estonianpizzaaBE.security.jwt.JwtUtils;
// import com.example.estonianpizzaaBE.security.services.UserDetailsImpl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {
//   @Autowired
//   AuthenticationManager authenticationManager;

//   @Autowired
//   CustomerRepository customerRepository;

//   @Autowired
//   RoleRepository roleRepository;

//   @Autowired
//   PasswordEncoder encoder;

//   @Autowired
//   JwtUtils jwtUtils;

//   @PostMapping("/signin")
//   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//     Authentication authentication = authenticationManager.authenticate(
//         new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//     SecurityContextHolder.getContext().setAuthentication(authentication);
//     String jwt = jwtUtils.generateJwtToken(authentication);
    
//     UserDetailsImpl customerDetails = (UserDetailsImpl) authentication.getPrincipal();    
//     List<String> roles = customerDetails.getAuthorities().stream()
//         .map(item -> item.getAuthority())
//         .collect(Collectors.toList());

//     return ResponseEntity.ok(new jwtResponse(jwt, 
//                          customerDetails.getId(), 
//                          customerDetails.getUsername(), 
//                          customerDetails.getEmail(), 
//                          customerDetails.getPhone_number(),
//                          roles));
//   }


//   @PostMapping("/signup")
//   public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//     if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
//       return ResponseEntity
//           .badRequest()
//           .body(new MessageResponse("Error: Username is already taken!"));
//     }

//     if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
//       return ResponseEntity
//           .badRequest()
//           .body(new MessageResponse("Error: Email is already in use!"));
//     }

//     // Create new user's account
//     Customer customer = new Customer(signUpRequest.getUsername(), 
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone_number());

//     Set<String> strRoles = signUpRequest.getRole();
//     Set<Role> roles = new HashSet<>();

//     if (strRoles == null) {
//       Role customerRole = RoleRepository.findByName(ERole.ROLE_CUSTOMER)
//           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//       roles.add(customerRole);
//     } else {
//       strRoles.forEach(role -> {
//         switch (role) {
//         case "kitchen":
//           Role kitchenRole = RoleRepository.findByName(ERole.ROLE_KITCHEN)
//               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//           roles.add(kitchenRole);

//           break;
//         case "dev":
//           Role devRole = RoleRepository.findByName(ERole.ROLE_DELIVERY)
//               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//           roles.add(devRole);

//           break;
//         default:
//           Role customerRole = RoleRepository.findByName(ERole.ROLE_CUSTOMER)
//               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//           roles.add(customerRole);
//         }
//       });
//     }

//     customer.setRoles(roles);
//     customerRepository.save(customer);

//     return ResponseEntity.ok(new MessageResponse("customer registered successfully!"));
//   }
// }
