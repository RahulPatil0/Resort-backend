package com.resortbooking.application.services;

import com.resortbooking.application.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByPhone(String phone);

    boolean emailExists(String email);

    boolean phoneExists(String phone);

    List<User> getAllUsers();

    void deleteUser(Long id);
}
