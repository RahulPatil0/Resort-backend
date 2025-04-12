package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.NotificationDTO;
import com.resortbooking.application.models.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDTO toDto(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setRead(notification.isRead());
        dto.setTimestamp(notification.getTimestamp());
        dto.setUserId(notification.getUser().getId());
        return dto;
    }

    public Notification toEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setId(dto.getId());
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRead(dto.isRead());
        notification.setTimestamp(dto.getTimestamp());
        return notification;
    }
}
