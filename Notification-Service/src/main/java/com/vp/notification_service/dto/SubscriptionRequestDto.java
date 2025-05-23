package com.vp.notification_service.dto;

import lombok.Data;

@Data
public class SubscriptionRequestDto {
    private Long userId;
    private Long categoryId;
}
