package com.resortbooking.application.dao;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    List<Rooms> findByIsAvailableTrue();
    List<Rooms> findByHotel(Hotel hotel);
    List<Rooms> findByHotelAndIsAvailableTrue(Hotel hotel);
    List<Rooms> findByHotelAndTypeIgnoreCase(Hotel hotel, String type);
}
