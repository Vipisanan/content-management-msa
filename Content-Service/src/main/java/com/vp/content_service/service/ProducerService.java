package com.vp.content_service.service;

import com.vp.content_service.dto.ContentEvent;

public interface ProducerService {
    void sendMessage(String topic, ContentEvent contentEvent);
}
