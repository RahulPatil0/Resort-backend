package com.resortbooking.application.dao;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelPhotosRepository extends JpaRepository<HotelPhotos, Long> {

//    List<HotelPhotos> findByHotel(Hotel hotel);
//
//    List<HotelPhotos> findByHotelOrderByUploadedAtDesc(Hotel hotel);
//
//    List<HotelPhotos> findByHotelAndIsPrimaryTrue(Hotel hotel); // To fetch main display image
}
