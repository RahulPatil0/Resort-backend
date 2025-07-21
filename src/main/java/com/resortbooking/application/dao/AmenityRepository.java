package com.resortbooking.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortbooking.application.models.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
