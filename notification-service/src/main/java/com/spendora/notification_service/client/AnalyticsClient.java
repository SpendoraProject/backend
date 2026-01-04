package com.spendora.notification_service.client;

import com.spendora.notification_service.dto.ExpenseSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AnalyticsClient {
    private final RestTemplate restTemplate;

    private static final String ANALYTICS_URL =
            "http://localhost:8084/api/analytics/monthly-summary";

    public ExpenseSummaryDTO getMonthlySummary(String userId, int year, int month) {

        String url = ANALYTICS_URL +
                "?userId=" + userId +
                "&year=" + year +
                "&month=" + month;

        return restTemplate.getForObject(url, ExpenseSummaryDTO.class);
    }
}
