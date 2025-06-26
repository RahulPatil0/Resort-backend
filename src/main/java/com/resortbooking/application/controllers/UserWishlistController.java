package com.resortbooking.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import com.resortbooking.application.models.UserWishlist;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.UserWishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class UserWishlistController {

    @Autowired
    private UserWishlistService wishlistService;
    

    @GetMapping("/list")
    public ResortBookingResponse getUserWishlist(@RequestBody User user) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";

        try {
            List<UserWishlist> wishlist = wishlistService.getUserWishlist(user);
            status = HttpStatus.OK;
            return new ResortBookingResponse(wishlist, status);
        } catch (Exception e) {
            message = "Error retrieving wishlist: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    @PostMapping("/add")
    public ResortBookingResponse addToWishlist(@RequestBody User user, @RequestParam Long hotelId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";

        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            UserWishlist wishlist = wishlistService.addToWishlist(user, hotel);
            status = HttpStatus.OK;
            return new ResortBookingResponse(wishlist, status);
        } catch (ResortBookingException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = "Error adding to wishlist: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    @DeleteMapping("/remove")
    public ResortBookingResponse removeFromWishlist(@RequestBody User user, @RequestParam Long hotelId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";

        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            wishlistService.removeFromWishlist(user, hotel);
            status = HttpStatus.OK;
            return new ResortBookingResponse("Hotel removed from wishlist", status);
        } catch (ResortBookingException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = "Error removing from wishlist: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }


}
