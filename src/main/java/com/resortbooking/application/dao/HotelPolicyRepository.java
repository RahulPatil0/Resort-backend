package com.resortbooking.application.dao;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelPolicyRepository extends JpaRepository<HotelPolicy, Long> {
    List<HotelPolicy> findByHotel(Hotel hotel);
}
