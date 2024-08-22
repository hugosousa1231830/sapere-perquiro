package com.kafka.kafka_eda.domain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/visit")
public class CustomerVisitController {

    private final CustomerVisitEventProducer eventProducer;

    public CustomerVisitController(CustomerVisitEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping()
    public String sendCustomerVisitEvent() {
        try {
            CustomerVisitEvent event = CustomerVisitEvent.builder()
                    .customerID(UUID.randomUUID().toString())
                    .dateTime(LocalDateTime.now())
                    .build();
            eventProducer.sendEvent(event);
            return "I AM THE PRODUCER AND I JUST SENT A FRESH NEW EVENT!";
        } catch (Exception e) {
            return "Failed to send event: " + e.getMessage();
        }
    }
}
