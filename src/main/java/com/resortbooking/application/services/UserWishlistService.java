package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import com.resortbooking.application.models.UserWishlist;

public interface UserWishlistService {

	UserWishlist addToWishlist(User user, Hotel hotel);

	void removeFromWishlist(User user, Hotel hotel);

	List<UserWishlist> getUserWishlist(User user);

	boolean isHotelInWishlist(User user, Hotel hotel);

	long getWishlistCount(User user);
}
