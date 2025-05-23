package com.vp.notification_service.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vp.notification_service.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Kafka Listener for Content Events
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentEvent {
    private Long contentId;
    private Long writerId;
    private NotificationType type; // "PUBLISHED", "UPDATED", "DELETED"
    private List<Long> categoryIds;
    private String title;
    private String summary;
}