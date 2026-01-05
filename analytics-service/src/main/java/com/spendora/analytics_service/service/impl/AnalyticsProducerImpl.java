package com.spendora.analytics_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spendora.analytics_service.dto.ExpenseSummaryDTO;
import com.spendora.analytics_service.service.AnalyticsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsProducerImpl implements AnalyticsProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper; // Injected Jackson mapper
    private static final String TOPIC = "spending-alerts";

    @Override
    public void sendOverspendEvent(ExpenseSummaryDTO dto) {
        try {
            // Convert DTO to JSON string
            String json = objectMapper.writeValueAsString(dto);

            // Send JSON to Kafka
            kafkaTemplate.send(TOPIC, dto.getUserId(), json);

            System.out.println("Published overspend event for user: " + dto.getUserId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
