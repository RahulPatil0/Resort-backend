package com.resortbooking.application.services;

import com.resortbooking.application.dto.NotificationDTO;
import com.resortbooking.application.exception.ResortBookingException;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO dto) throws ResortBookingException;
    
    List<NotificationDTO> getNotificationsByUser(Long userId) throws ResortBookingException;
    
    NotificationDTO markAsRead(Long id) throws ResortBookingException;
    
    void deleteNotification(Long id) throws ResortBookingException;
}
