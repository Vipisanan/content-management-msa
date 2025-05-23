package com.vp.notification_service.service;

import java.util.List;

public interface SubscriptionService {
    List<Long> findUserIdsByCategoryId(Long categoryId);
}
