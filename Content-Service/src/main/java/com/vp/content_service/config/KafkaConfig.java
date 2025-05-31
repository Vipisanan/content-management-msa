package com.vp.content_service.config;

import com.vp.content_service.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.CONTENT_EVENT_KTP)
                .partitions(3)    // number of partitions (for scalability)
                .replicas(3)       // replication factor (for high availability)
                .build();
    }
}