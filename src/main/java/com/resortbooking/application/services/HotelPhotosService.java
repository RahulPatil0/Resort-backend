package com.resortbooking.application.services;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Media;

import java.util.List;
import java.util.Optional;

public interface HotelPhotosService {

    Media savePhoto(Media photo) throws ResortBookingException;

    Optional<Media> getPhotoById(Long id) throws ResortBookingException;

    List<Media> getPhotosByHotel(Hotel hotel) throws ResortBookingException;

    List<Media> getPhotosByHotelSorted(Hotel hotel) throws ResortBookingException;

    Optional<Media> getPrimaryPhotoByHotel(Hotel hotel) throws ResortBookingException;

    void deletePhoto(Long id) throws ResortBookingException;
}
