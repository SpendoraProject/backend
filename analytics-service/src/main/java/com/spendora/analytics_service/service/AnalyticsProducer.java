package com.spendora.analytics_service.service;

import com.spendora.analytics_service.dto.ExpenseSummaryDTO;

public interface AnalyticsProducer {
    void sendOverspendEvent(ExpenseSummaryDTO dto);
}
