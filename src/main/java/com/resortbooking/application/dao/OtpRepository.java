package com.resortbooking.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortbooking.application.models.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{

	Optional<Otp> findByKeyAndOtp(String key, String inputOtp);

}
