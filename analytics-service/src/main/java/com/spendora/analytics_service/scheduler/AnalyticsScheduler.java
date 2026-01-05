package com.spendora.analytics_service.scheduler;

import com.spendora.analytics_service.dto.ExpenseSummaryDTO;
import com.spendora.analytics_service.service.AnalyticsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticsScheduler {

    private final AnalyticsProducer producer;

    @Scheduled(cron = "0 03 21 * * ?") // every day midnight
    public void checkOverspending() {
        // Example user & dummy calculation
        ExpenseSummaryDTO dto = new ExpenseSummaryDTO(
                "user123",
                "January",
                1200,
                50,
                200,
                5
        );

        // If user exceeded threshold
        if(dto.getTotalExpense() > 1000) { // 1000 = example limit
            producer.sendOverspendEvent(dto);
        }
    }
}

