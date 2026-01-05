package com.spendora.analytics_service.controller;

import com.spendora.analytics_service.dto.ExpenseSummaryDTO;
import com.spendora.analytics_service.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/analytics/monthly-summary")
    public List<ExpenseSummaryDTO> getMonthlySummary(@RequestHeader("X-User-Id") String userId) {
        return analyticsService.getMonthlySummary(userId);
    }
}
