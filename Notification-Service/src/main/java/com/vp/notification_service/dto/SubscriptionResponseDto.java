package com.vp.notification_service.dto;

import lombok.Data;

@Data
public class SubscriptionResponseDto {
    private Long subscriptionId;
    private Long userId;
    private Long categoryId;
    private String message;
}
