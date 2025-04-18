//package com.resortbooking.application.dao;
//
//import com.resortbooking.application.models.UserWishlist;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.models.Hotel;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface UserWishlistRepository extends JpaRepository<UserWishlist, Long> {
//
//    List<UserWishlist> findByUser(User user);
//
//    Optional<UserWishlist> findByUserAndHotel(User user, Hotel hotel);
//
//    void deleteByUserAndHotel(User user, Hotel hotel);
//
//    long countByUser(User user);
//}
