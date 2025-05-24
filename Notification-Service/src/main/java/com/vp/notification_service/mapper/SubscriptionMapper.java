package com.vp.notification_service.mapper;


import com.vp.notification_service.dto.SubscriptionResponseDto;
import com.vp.notification_service.dto.UserSubscriptionResponseDto;
import com.vp.notification_service.model.Subscription;

public class SubscriptionMapper {

    public static SubscriptionResponseDto toDto(Subscription entity, String message) {
        SubscriptionResponseDto dto = new SubscriptionResponseDto();
        dto.setSubscriptionId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setCategoryId(entity.getCategoryId());
        dto.setMessage(message);
        return dto;
    }

    public static UserSubscriptionResponseDto toDto(Subscription entity) {
        UserSubscriptionResponseDto dto = new UserSubscriptionResponseDto();
        dto.setSubscriptionId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setCategoryId(entity.getCategoryId());
        return dto;
    }
}