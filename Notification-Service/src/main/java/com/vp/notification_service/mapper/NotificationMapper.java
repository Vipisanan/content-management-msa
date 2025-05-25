package com.vp.notification_service.mapper;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.model.Notification;

import java.time.format.DateTimeFormatter;

public class NotificationMapper {

    public static NotificationDto toDto(Notification notification) {
        if (notification == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = notification.getCreatedAt().format(formatter);
        return NotificationDto.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .contentId(notification.getContentId())
                .categoryId(notification.getCategoryId())
                .type(notification.getType())
                .message(notification.getMessage())
                .isRead(notification.getIsRead())
                .createdAt(dateString)
                .build();
    }
}