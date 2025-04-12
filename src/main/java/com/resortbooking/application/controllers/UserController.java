//package com.resortbooking.application.controllers;
//
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.services.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//@CrossOrigin(origins = "*") 
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    // Register a new user
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        if (userService.emailExists(user.getEmail())) {
//            return ResponseEntity.badRequest().body(null); // Email already exists
//        }
//        if (userService.phoneExists(user.getPhoneNumber())) {
//            return ResponseEntity.badRequest().body(null); // Phone already exists
//        }
//
//        // Optional: Add validation or password hashing here
//        User savedUser = userService.registerUser(user);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    // Get user by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        Optional<User> userOpt = userService.getUserById(id);
//        return userOpt.map(ResponseEntity::ok)
//                      .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get user by email
//    @GetMapping("/email")
//    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
//        Optional<User> userOpt = userService.getUserByEmail(email);
//        return userOpt.map(ResponseEntity::ok)
//                      .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get user by phone number
//    @GetMapping("/phone")
//    public ResponseEntity<User> getUserByPhone(@RequestParam String phone) {
//        Optional<User> userOpt = userService.getUserByPhone(phone);
//        return userOpt.map(ResponseEntity::ok)
//                      .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get all users
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    // Delete user by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        Optional<User> userOpt = userService.getUserById(id);
//        if (userOpt.isPresent()) {
//            userService.deleteUser(id);
//            return ResponseEntity.ok("User deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
package com.resortbooking.application.controllers; 

import com.resortbooking.application.models.User;
import com.resortbooking.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") 
public class UserController {

    @Autowired
    private UserService userService;

 // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            if (userService.emailExists(user.getEmail())) {
                return ResponseEntity.badRequest().body(null); // Email already exists
            }
            if (userService.phoneExists(user.getPhoneNumber())) {
                return ResponseEntity.badRequest().body(null); // Phone already exists
            }

            // Optional: Add validation or password hashing here
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle unexpected errors
        }
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            Optional<User> userOpt = userService.getUserById(id);
            return userOpt.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle unexpected errors
        }
    }

    // Get user by email
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        try {
            Optional<User> userOpt = userService.getUserByEmail(email);
            return userOpt.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle unexpected errors
        }
    }

    // Get user by phone number
    @GetMapping("/phone")
    public ResponseEntity<User> getUserByPhone(@RequestParam String phone) {
        try {
            Optional<User> userOpt = userService.getUserByPhone(phone);
            return userOpt.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle unexpected errors
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle unexpected errors
        }
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            Optional<User> userOpt = userService.getUserById(id);
            if (userOpt.isPresent()) {
                userService.deleteUser(id);
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred while deleting user.");
        }
    }
}