package com.vp.notification_service.service;

import com.vp.notification_service.dto.SubscriptionRequestDto;
import com.vp.notification_service.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {
    List<Long> findUserIdsByCategoryId(Long categoryId);

    SubscriptionResponseDto subscribe(SubscriptionRequestDto requestDto);

    SubscriptionResponseDto unsubscribe(SubscriptionRequestDto requestDto);

}
