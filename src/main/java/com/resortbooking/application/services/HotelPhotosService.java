package com.resortbooking.application.services;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;

import java.util.List;
import java.util.Optional;

public interface HotelPhotosService {

    HotelPhotos savePhoto(HotelPhotos photo);

    Optional<HotelPhotos> getPhotoById(Long id);

    List<HotelPhotos> getPhotosByHotel(Hotel hotel);

    List<HotelPhotos> getPhotosByHotelSorted(Hotel hotel);

    Optional<HotelPhotos> getPrimaryPhotoByHotel(Hotel hotel);

    void deletePhoto(Long id);
}
