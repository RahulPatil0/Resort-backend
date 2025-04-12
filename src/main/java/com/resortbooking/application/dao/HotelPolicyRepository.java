package com.resortbooking.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelPolicyRepository extends JpaRepository<com.resortbooking.application.models.HotelPolicy, Long> {
    List<com.resortbooking.application.models.HotelPolicy> findByHotelId(Long hotelId);
}
