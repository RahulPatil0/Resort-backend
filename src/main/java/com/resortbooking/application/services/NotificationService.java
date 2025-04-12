package com.resortbooking.application.services;

import com.resortbooking.application.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO dto);
    List<NotificationDTO> getNotificationsByUser(Long userId);
    NotificationDTO markAsRead(Long id);
    void deleteNotification(Long id);
}
