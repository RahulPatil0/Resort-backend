//package com.resortbooking.application.services.impl;
//
//import com.resortbooking.application.dao.UserRepository;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.services.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User registerUser(User user) {
//        // Optional: hash password, validate email format, etc.
//        return userRepository.save(user);
//    }
//
//    @Override
//    public Optional<User> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public Optional<User> getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Override
//    public Optional<User> getUserByPhone(String phone) {
//        return userRepository.findByPhoneNumber(phone);
//    }
//
//    @Override
//    public boolean emailExists(String email) {
//        return userRepository.existsByEmail(email);
//    }
//
//    @Override
//    public boolean phoneExists(String phone) {
//        return userRepository.existsByPhoneNumber(phone);
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//}

package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.UserRepository;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        try {
            // Optional: hash password, validate email format, etc.
            return userRepository.save(user);
        } catch (Exception e) {
            // Log the exception (assuming a logger is set up)
            System.err.println("Error registering user: " + e.getMessage());
            throw new RuntimeException("Error registering user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching user by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching user by ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching user by email: " + e.getMessage());
            throw new RuntimeException("Error fetching user by email: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        try {
            return userRepository.findByPhoneNumber(phone);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching user by phone: " + e.getMessage());
            throw new RuntimeException("Error fetching user by phone: " + e.getMessage());
        }
    }

    @Override
    public boolean emailExists(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error checking if email exists: " + e.getMessage());
            throw new RuntimeException("Error checking if email exists: " + e.getMessage());
        }
    }

    @Override
    public boolean phoneExists(String phone) {
        try {
            return userRepository.existsByPhoneNumber(phone);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error checking if phone exists: " + e.getMessage());
            throw new RuntimeException("Error checking if phone exists: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching all users: " + e.getMessage());
            throw new RuntimeException("Error fetching all users: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting user: " + e.getMessage());
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
    }
}

