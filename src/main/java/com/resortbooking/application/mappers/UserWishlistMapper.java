//package com.resortbooking.application.mappers;
//
//import com.resortbooking.application.dto.HotelDto;
//import com.resortbooking.application.dto.UserDTO;
//import com.resortbooking.application.dto.UserWishlistDTO;
//import com.resortbooking.application.models.UserWishlist;
//
//public class UserWishlistMapper {
//
//    public static UserWishlistDTO toDTO(UserWishlist wishlist, UserDTO userDto, HotelDto hotelDto) {
//        if (wishlist == null) {
//            return null;
//        }
//
//        UserWishlistDTO dto = new UserWishlistDTO();
//        dto.setId(wishlist.getId());
//        dto.setCreatedAt(wishlist.getCreatedAt());
//        dto.setUser(userDto); // user entity -> UserDTO (map separately)
//        dto.setHotel(hotelDto); // hotel entity -> HotelDTO (map separately)
//
//        return dto;
//    }
//
//    public static UserWishlist toEntity(UserWishlistDTO dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        UserWishlist wishlist = new UserWishlist();
//        wishlist.setId(dto.getId());
//        wishlist.setCreatedAt(dto.getCreatedAt());
//
//
//        return wishlist;
//    }
//}
