package com.vp.content_service.kafka;

import com.vp.content_service.dto.ContentEvent;
import com.vp.content_service.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService implements ProducerService {

    private final KafkaTemplate<String, ContentEvent> kafkaTemplate;

    @Override
    public void sendMessage(String topic, ContentEvent contentEvent) {
        kafkaTemplate.send(topic, contentEvent);
    }
}
