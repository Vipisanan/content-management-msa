package com.vp.notification_service.mapper;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.model.Notification;

public class NotificationMapper {

    public static NotificationDto toDto(Notification notification) {
        if (notification == null) return null;
        return NotificationDto.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .contentId(notification.getContentId())
                .categoryId(notification.getCategoryId())
                .type(notification.getType())
                .message(notification.getMessage())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}