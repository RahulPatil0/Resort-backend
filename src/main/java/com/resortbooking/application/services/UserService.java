package com.resortbooking.application.services;

import com.resortbooking.application.dto.UserDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(UserDTO dto) throws ResortBookingException;

    Optional<User> getUserById(Long id) throws ResortBookingException;

    Optional<User> getUserByEmail(String email) throws ResortBookingException;

    Optional<User> getUserByPhone(String phone) throws ResortBookingException;

    boolean emailExists(String email) throws ResortBookingException;

    boolean phoneExists(String phone) throws ResortBookingException;

    List<User> getAllUsers() throws ResortBookingException;

    void deleteUser(Long id) throws ResortBookingException;
}
