package com.resortbooking.application.dao;

import com.resortbooking.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Check existence by email
    boolean existsByEmail(String email);

    // Check existence by phone
    boolean existsByPhoneNumber(String phoneNumber);
}
