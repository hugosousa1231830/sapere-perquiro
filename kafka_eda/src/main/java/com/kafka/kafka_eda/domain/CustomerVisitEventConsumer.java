package com.kafka.kafka_eda.domain;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitEventConsumer {

    @KafkaListener(topics = "visitor")
    public void listen(String message) {
        System.out.println("I AM THE CONSUMER AND I LISTENED TO THIS: " + message);
        // Additional logic to handle the consumed message can be added here
    }
}
