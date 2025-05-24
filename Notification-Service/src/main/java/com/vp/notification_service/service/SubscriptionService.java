package com.vp.notification_service.service;

import com.vp.notification_service.dto.SubscriptionRequestDto;
import com.vp.notification_service.dto.SubscriptionResponseDto;
import com.vp.notification_service.dto.UserSubscriptionResponseDto;
import com.vp.notification_service.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Long> findUserIdsByCategoryId(Long categoryId);

    SubscriptionResponseDto subscribe(SubscriptionRequestDto requestDto);

    SubscriptionResponseDto unsubscribe(SubscriptionRequestDto requestDto);

    List<UserSubscriptionResponseDto> getAllSubscriptionsByUserId(Long userId);


}
