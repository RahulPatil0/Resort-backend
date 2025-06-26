package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import com.resortbooking.application.models.UserWishlist;

public interface UserWishlistService {

	UserWishlist addToWishlist(User user, Hotel hotel) throws ResortBookingException;

	void removeFromWishlist(User user, Hotel hotel) throws ResortBookingException;

	List<UserWishlist> getUserWishlist(User user) throws ResortBookingException;

	boolean isHotelInWishlist(User user, Hotel hotel) throws ResortBookingException;

	long getWishlistCount(User user) throws ResortBookingException;
}
