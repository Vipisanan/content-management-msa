package com.vp.notification_service.dto;

import lombok.Data;

@Data
public class UserSubscriptionResponseDto {
    private Long subscriptionId;
    private Long userId;
    private Long categoryId;
}
