package com.resortbooking.application.services;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;

import java.util.List;
import java.util.Optional;

public interface HotelPhotosService {

    HotelPhotos savePhoto(HotelPhotos photo) throws ResortBookingException;

    Optional<HotelPhotos> getPhotoById(Long id) throws ResortBookingException;

    List<HotelPhotos> getPhotosByHotel(Hotel hotel) throws ResortBookingException;

    List<HotelPhotos> getPhotosByHotelSorted(Hotel hotel) throws ResortBookingException;

    Optional<HotelPhotos> getPrimaryPhotoByHotel(Hotel hotel) throws ResortBookingException;

    void deletePhoto(Long id) throws ResortBookingException;
}
