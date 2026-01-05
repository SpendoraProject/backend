package com.spendora.analytics_service.service;

import com.spendora.analytics_service.dto.ExpenseSummaryDTO;

import java.util.List;

public interface AnalyticsService {
    List<ExpenseSummaryDTO> getMonthlySummary(String userId);
}
