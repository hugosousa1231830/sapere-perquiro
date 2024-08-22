package com.kafka.kafka_eda.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CustomerVisitEventProducer(KafkaTemplate<String, String> kafkaTemplate,
                                      ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEvent(CustomerVisitEvent event) throws JsonProcessingException {
        final String payload = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("visitor", payload);
    }
}
