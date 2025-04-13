package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.HotelPhotosRepository;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
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
    public HotelPhotos savePhoto(HotelPhotos photo) {
        try {
            return hotelPhotosRepository.save(photo);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error saving photo: " + e.getMessage());
            throw new RuntimeException("Error saving photo: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelPhotos> getPhotoById(Long id) {
        try {
            return hotelPhotosRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching photo by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching photo by ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelPhotos> getPhotosByHotel(Hotel hotel) {
        try {
//            return hotelPhotosRepository.findByHotel(hotel);
        	return null;
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching photos by hotel: " + e.getMessage());
            throw new RuntimeException("Error fetching photos by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelPhotos> getPhotosByHotelSorted(Hotel hotel) {
        try {
//            return hotelPhotosRepository.findByHotelOrderByUploadedAtDesc(hotel);
        	return null;
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching sorted photos by hotel: " + e.getMessage());
            throw new RuntimeException("Error fetching sorted photos by hotel: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelPhotos> getPrimaryPhotoByHotel(Hotel hotel) {
        try {
//            List<HotelPhotos> primaryList = hotelPhotosRepository.findByHotelAndIsPrimaryTrue(hotel);
//            return primaryList.isEmpty() ? Optional.empty() : Optional.of(primaryList.get(0));
        	return null;
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching primary photo by hotel: " + e.getMessage());
            throw new RuntimeException("Error fetching primary photo by hotel: " + e.getMessage());
        }
    }

    @Override
    public void deletePhoto(Long id) {
        try {
            hotelPhotosRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting photo: " + e.getMessage());
            throw new RuntimeException("Error deleting photo: " + e.getMessage());
        }
    }
}
