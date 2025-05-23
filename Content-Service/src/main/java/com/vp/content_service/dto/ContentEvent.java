package com.vp.content_service.dto;

import com.vp.content_service.dto.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

// Kafka Listener for Content Events
@Data
@Builder
public class ContentEvent {
    private Long contentId;
    private Long writerId;
    private NotificationType type; // "PUBLISHED", "UPDATED", "DELETED"
    private List<Long> categoryIds;
    private String title;
    private String summary;
}