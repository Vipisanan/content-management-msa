package com.vp.notification_service.dto;

import com.vp.notification_service.model.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
    private Long id;
    private Long userId;
    private Long categoryId;
    private Long contentId;
    private NotificationType type;
    private String message;
    private Boolean isRead;
    private String createdAt; // ISO date string, optional
}