package com.resortbooking.application.dao;

import com.resortbooking.application.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);
    boolean existsByName(String name);
}
