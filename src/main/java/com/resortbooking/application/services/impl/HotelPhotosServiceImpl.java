package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.HotelPhotosRepository;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Media;
import com.resortbooking.application.services.HotelPhotosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelPhotosServiceImpl implements HotelPhotosService {

    @Autowired
    private HotelPhotosRepository hotelPhotosRepository;

    @Override
    public Media savePhoto(Media photo) throws ResortBookingException{
        try {
            return hotelPhotosRepository.save(photo);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error saving photo: " + e.getMessage());
            throw new ResortBookingException("Error saving photo: " + e.getMessage());
        }
    }

    @Override
    public Optional<Media> getPhotoById(Long id) throws ResortBookingException{
        try {
            return hotelPhotosRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching photo by ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching photo by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Media> getPhotosByHotel(Hotel hotel) throws ResortBookingException{
        try {
//            return hotelPhotosRepository.findByHotel(hotel);
        	return null;
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching photos by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching photos by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<Media> getPhotosByHotelSorted(Hotel hotel) throws ResortBookingException{
        try {
//            return hotelPhotosRepository.findByHotelOrderByUploadedAtDesc(hotel);
        	return null;
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching sorted photos by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching sorted photos by hotel: " + e.getMessage());
        }
    }

    @Override
    public Optional<Media> getPrimaryPhotoByHotel(Hotel hotel) throws ResortBookingException{
        try {
//            List<HotelPhotos> primaryList = hotelPhotosRepository.findByHotelAndIsPrimaryTrue(hotel);
//            return primaryList.isEmpty() ? Optional.empty() : Optional.of(primaryList.get(0));
        	return null;
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching primary photo by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching primary photo by hotel: " + e.getMessage());
        }
    }

    @Override
    public void deletePhoto(Long id) throws ResortBookingException{
        try {
            hotelPhotosRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting photo: " + e.getMessage());
            throw new ResortBookingException("Error deleting photo: " + e.getMessage());
        }
    }
}
