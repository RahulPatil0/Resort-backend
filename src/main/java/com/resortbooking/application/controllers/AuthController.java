// Assuming you're using Spring Boot with JWT support
// Directory: com.resortbooking.application.controllers

package com.resortbooking.application.controllers;

import com.resortbooking.application.dto.*;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.JwtMapper;
import com.resortbooking.application.models.User;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	 
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtMapper jwtMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResortBookingResponse<?> register(@RequestBody UserDTO dto) {
    	String message;
        try {
            userService.registerUser(dto);
            message = "User registered successfully";
            return new ResortBookingResponse<String>(message, HttpStatus.BAD_REQUEST);
        } catch (ResortBookingException e) {
            message = e.getMessage();
            logger.error("Error occurred while creating hotel: {}", e.getMessage(), e);
        } catch (Exception e) {
            message = "Error creating hotel: " + e.getMessage();
            logger.error("Error occurred while creating hotel: {}", e.getMessage(), e);
        }
        return new ResortBookingResponse<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            String token = jwtMapper.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            userService.resetPassword(request.getEmail());
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process request");
        }
    }
}
