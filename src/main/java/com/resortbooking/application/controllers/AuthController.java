//// Assuming you're using Spring Boot with JWT support
//// Directory: com.resortbooking.application.controllers
//
//package com.resortbooking.application.controllers;
//
//import com.resortbooking.application.dto.*;
//import com.resortbooking.application.exception.ResortBookingException;
//import com.resortbooking.application.mappers.JwtMapper;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.response.ResortBookingResponse;
//import com.resortbooking.application.services.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtMapper jwtMapper;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        try {
//            userService.registerUser(request);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//        } catch (ResortBookingException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        try {
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            String token = jwtMapper.generateToken(request.getUsername());
//            return ResponseEntity.ok(new LoginResponse(token));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
//        try {
//            userService.resetPassword(request.getEmail());
//            return ResponseEntity.ok("Password reset instructions sent to your email.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process request");
//        }
//    }
//}
