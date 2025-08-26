
package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.RolesRepository;
import com.resortbooking.application.dao.UserRepository;
import com.resortbooking.application.dto.UserDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.UserMapper;
import com.resortbooking.application.models.Roles;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public User registerUser(UserDTO dto) throws ResortBookingException {
        try {
        	User user = UserMapper.toEntity(dto);
        	if (userRepository.existsByEmail(user.getEmail())) {
                throw new ResortBookingException("Email already exists!");
            }

            // Check if phone number already exists
            if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
                throw new ResortBookingException("Phone number already exists!");
            }

            // Set defaults
            if (user.getIsGoogleUser() == null) {
                user.setIsGoogleUser(false);
            }
            user.setVerified(false);
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            
            Optional<Roles> userRole = rolesRepository.findByRole("USER");
			user.getRoles().add(userRole.get());
            
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResortBookingException("Error registering user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserById(Long id) throws ResortBookingException {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new ResortBookingException("Error fetching user by ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws ResortBookingException{
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching user by email: " + e.getMessage());
            throw new ResortBookingException("Error fetching user by email: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserByPhone(String phone) throws ResortBookingException{
        try {
            return userRepository.findByPhoneNumber(phone);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching user by phone: " + e.getMessage());
            throw new ResortBookingException("Error fetching user by phone: " + e.getMessage());
        }
    }

    @Override
    public boolean emailExists(String email) throws ResortBookingException{
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error checking if email exists: " + e.getMessage());
            throw new ResortBookingException("Error checking if email exists: " + e.getMessage());
        }
    }

    @Override
    public boolean phoneExists(String phone) throws ResortBookingException{
        try {
            return userRepository.existsByPhoneNumber(phone);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error checking if phone exists: " + e.getMessage());
            throw new ResortBookingException("Error checking if phone exists: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws ResortBookingException{
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching all users: " + e.getMessage());
            throw new ResortBookingException("Error fetching all users: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) throws ResortBookingException{
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting user: " + e.getMessage());
            throw new ResortBookingException("Error deleting user: " + e.getMessage());
        }
    }
}

