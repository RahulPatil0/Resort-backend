//package com.resortbooking.application.services.impl;
//
//import com.resortbooking.application.dao.UserWishlistRepository;
//import com.resortbooking.application.exception.ResortBookingException;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.models.Hotel;
//import com.resortbooking.application.models.UserWishlist;
//import com.resortbooking.application.services.UserWishlistService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserWishlistServiceImpl implements UserWishlistService {
//
//    @Autowired
//    private UserWishlistRepository wishlistRepository;
//
//    @Override
//    public UserWishlist addToWishlist(User user, Hotel hotel) throws ResortBookingException{
//        // Check if it already exists
//        Optional<UserWishlist> existing = wishlistRepository.findByUserAndHotel(user, hotel);
//        if (existing.isPresent()) {
//            return existing.get();
//        }
//
//        UserWishlist wishlist = new UserWishlist();
//        wishlist.setUser(user);
//        wishlist.setHotel(hotel);
//        wishlist.setCreatedAt(LocalDateTime.now());
//        return wishlistRepository.save(wishlist);
//    }
//
//    @Override
//    public void removeFromWishlist(User user, Hotel hotel) throws ResortBookingException{
//        wishlistRepository.deleteByUserAndHotel(user, hotel);
//    }
//
//    @Override
//    public List<UserWishlist> getUserWishlist(User user) throws ResortBookingException{
//        return wishlistRepository.findByUser(user);
//    }
//
//    @Override
//    public boolean isHotelInWishlist(User user, Hotel hotel) throws ResortBookingException{
//        return wishlistRepository.findByUserAndHotel(user, hotel).isPresent();
//    }
//
//    @Override
//    public long getWishlistCount(User user) throws ResortBookingException{
//        return wishlistRepository.countByUser(user);
//    }
//}
