package com.vp.notification_service.kafka;

import com.vp.notification_service.model.enums.NotificationType;
import lombok.Data;

import java.util.List;

// Kafka Listener for Content Events
@Data
public class ContentEvent {
    private Long contentId;
    private Long writerId;
    private NotificationType type; // "PUBLISHED", "UPDATED", "DELETED"
    private List<Long> categoryIds;
    private String title;
    private String summary;
}