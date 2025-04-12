//package com.resortbooking.application.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.resortbooking.application.models.Hotel;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.models.UserWishlist;
//import com.resortbooking.application.services.UserWishlistService;
//
//
//@RestController
//@RequestMapping("/api/wishlist")
//public class UserWishlistController {
//
//    @Autowired
//    private UserWishlistService wishlistService;
//
// 
//    @PostMapping("/add")
//    public ResponseEntity<UserWishlist> addToWishlist(@RequestBody User user, @RequestParam Long hotelId) {
//        Hotel hotel = new Hotel();
//        hotel.setId(hotelId);
//
//        UserWishlist wishlist = wishlistService.addToWishlist(user, hotel);
//        return ResponseEntity.ok(wishlist);
//    }
//
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<Void> removeFromWishlist(@RequestBody User user, @RequestParam Long hotelId) {
//        Hotel hotel = new Hotel();
//        hotel.setId(hotelId);
//
//        wishlistService.removeFromWishlist(user, hotel);
//        return ResponseEntity.noContent().build();
//    }
//
//   
//    @PostMapping("/list")
//    public ResponseEntity<List<UserWishlist>> getUserWishlist(@RequestBody User user) {
//        List<UserWishlist> wishlist = wishlistService.getUserWishlist(user);
//        return ResponseEntity.ok(wishlist);
//    }
//
//    
//    @PostMapping("/exists")
//    public ResponseEntity<Boolean> isHotelInWishlist(@RequestBody User user, @RequestParam Long hotelId) {
//        Hotel hotel = new Hotel();
//        hotel.setId(hotelId);
//
//        boolean exists = wishlistService.isHotelInWishlist(user, hotel);
//        return ResponseEntity.ok(exists);
//    }
//
//
//    @PostMapping("/count")
//    public ResponseEntity<Long> getWishlistCount(@RequestBody User user) {
//        long count = wishlistService.getWishlistCount(user);
//        return ResponseEntity.ok(count);
//    }
//}
package com.resortbooking.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import com.resortbooking.application.models.UserWishlist;
import com.resortbooking.application.services.UserWishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class UserWishlistController {

    @Autowired
    private UserWishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<UserWishlist> addToWishlist(@RequestBody User user, @RequestParam Long hotelId) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            UserWishlist wishlist = wishlistService.addToWishlist(user, hotel);
            return ResponseEntity.ok(wishlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle any unexpected errors
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromWishlist(@RequestBody User user, @RequestParam Long hotelId) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            wishlistService.removeFromWishlist(user, hotel);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Handle any unexpected errors
        }
    }

    @PostMapping("/list")
    public ResponseEntity<List<UserWishlist>> getUserWishlist(@RequestBody User user) {
        try {
            List<UserWishlist> wishlist = wishlistService.getUserWishlist(user);
            return ResponseEntity.ok(wishlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle any unexpected errors
        }
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> isHotelInWishlist(@RequestBody User user, @RequestParam Long hotelId) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            boolean exists = wishlistService.isHotelInWishlist(user, hotel);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(false); // Return false if an error occurs
        }
    }

    @PostMapping("/count")
    public ResponseEntity<Long> getWishlistCount(@RequestBody User user) {
        try {
            long count = wishlistService.getWishlistCount(user);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(0L); // Return 0 if an error occurs
        }
    }
}
